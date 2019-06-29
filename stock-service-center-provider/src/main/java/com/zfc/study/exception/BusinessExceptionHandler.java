package com.zfc.study.exception;

import com.zfc.study.util.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-14 11:07
 **/
@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler {

    /**
     * @Author zufeichao
     * @Description 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @Date 11:09 2019/6/14
     * @Param [binder]
     * @return void
     **/
    @InitBinder
    public void initBinder(WebDataBinder binder){
        log.info("请求有参数才进来，{}",binder.getRequiredFields());
    }

    /**
     * @Author zufeichao
     * @Description 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @Date 11:13 2019/6/14
     * @Param [model]
     * @return void
     **/
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("author","zufeichao");
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest request){
        ApiResult apiResult = new ApiResult();

        //业务异常
        if(e instanceof BusinessException){
            apiResult.setCode(((BusinessException) e).getCode());
            apiResult.setMsg(((BusinessException) e).getMsg());
        }else{
            //系统异常
            apiResult.setCode("500");
            apiResult.setMsg("请联系管理员，未知异常:"+e.getMessage());
        }

       /* 使用HttpServletRequest中的header检测请求是否为ajax,
        *如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        */
       String contentTypeHeader = request.getHeader("Content-Type");
       String acceptHeader = request.getHeader("Accept");
       String xRequestedWith = request.getHeader("X-Requested-With");
       if(contentTypeHeader != null && contentTypeHeader.contains("application/json")
               || (acceptHeader != null && acceptHeader.contains("application/json"))
               || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)){

           return apiResult;
       }else {
           ModelAndView modelAndView = new ModelAndView();
           modelAndView.addObject("msg", e.getMessage());
           modelAndView.addObject("url", request.getRequestURL());
           modelAndView.addObject("stackTrace", e.getStackTrace());
           modelAndView.setViewName("error");
           return modelAndView;
       }

    }



}
