package com.zfc.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author zufeichao
 * @Description //TODO
 *   EnableDiscoveryClient 用于启动服务发现功能
 *   EnableFeignClients 用于启动Fegin功能
 * @Date 17:37 2019/6/27
 * @Param
 * @return
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class StockServiceCenterProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockServiceCenterProviderApplication.class, args);
    }

}
