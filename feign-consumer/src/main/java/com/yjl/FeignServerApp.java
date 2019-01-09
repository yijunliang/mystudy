package com.yjl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Logger;
@SpringBootApplication//此注解标明这个类是springboot的启动类
@EnableDiscoveryClient
@EnableFeignClients //开启Feign支持功能
public class FeignServerApp  extends SpringBootServletInitializer {
	@Bean//配置全局日志级别
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
	
	public static void main(String[] args) {
		//启动springboot的方法
		SpringApplication.run(FeignServerApp.class, args);
	}
	
	@Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
