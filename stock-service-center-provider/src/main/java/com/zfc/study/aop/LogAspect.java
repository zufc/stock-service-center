package com.zfc.study.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zfc.study.aop.annotation.SysLog;
import com.zfc.study.service.SysLogService;
import com.zfc.study.stock.dto.SysLogDto;
import com.zfc.study.util.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description 使用@Aspect注解声明一个切面
 * @Date 2019-06-11 11:28
 **/
@Component
@Aspect
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private SysLogService sysLogService;

    /**
     * @Author zufeichao
     * @Description 这里我们使用注解的形式
     *              当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     *              切点表达式:   execution(...)
     * @Date 13:53 2019/6/11
     * execution (* com.zfc.study.controller..*.*(..))
     * @annotation(com.zfc.study.aop.annotation.SysLog)
     **/
    @Pointcut("execution (* com.zfc.study.controller..*.*(..))")
    public void apiLogAop(){}

    /**
     * @Author zufeichao
     * @Description 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     * @Date 13:55 2019/6/11
     * @Param [point]
     * @return java.lang.Object
     **/
    @Around("apiLogAop()")
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable{

        logger.info("日志统一打印 ↓ ↓ ↓ ↓ ↓ ↓ {}.{}() start ↓ ↓ ↓ ↓ ↓ ↓,参数:\n{}",
                point.getSignature().getDeclaringTypeName(),
                point.getSignature().getName(), argsToString(point.getArgs()));
        long  startTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - startTime;
        try {
            saveLog(point,time);
        }catch (Exception e){
            logger.info("切面保存日志失败：{}",e.getMessage());
        }

        return result;

    }

    /**
     * @Author zufeichao
     * @Description 保存日志
     * @Date 13:59 2019/6/11
     * @Param [joinPoint, time]
     * @return void
     **/
    private void saveLog(ProceedingJoinPoint joinPoint,long time){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        SysLogDto sysLogDto = new SysLogDto();
        sysLogDto.setExeuTime(time);
        sysLogDto.setCreateDate(DateUtils.formatDateTimeToString(LocalDateTime.now()));

        SysLog sysLog = method.getAnnotation(SysLog.class);
        if(sysLog != null ){
            //注解上的描述
            sysLogDto.setRemark(sysLog.value());
        }
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        sysLogDto.setClassName(className);
        sysLogDto.setMethodName(methodName);

        Object[] args = joinPoint.getArgs();
        try {
            List<String> list = Lists.newArrayList();
            for (Object o : args) {
                list.add(argsToString(o));
            }
            sysLogDto.setParams(list.toString());

        }catch (Exception e){
            logger.info("",e);
        }
        sysLogService.saveLog(sysLogDto);


    }

    private String argsToString(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            logger.error("切面参数json转换失败", e);
        }
        return String.valueOf(object);
    }


}
