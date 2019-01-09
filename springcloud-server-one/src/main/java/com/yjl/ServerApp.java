package com.yjl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication//此注解标明这个类是springboot的启动类
@MapperScan("com.yjl.mapper")//配置映射接口的所在路径，就不用为每个映射接口单独配置@MapperScan
@EnableDiscoveryClient
@EnableCircuitBreaker //开启断路器功能
public class ServerApp extends SpringBootServletInitializer {
	public static void main(String[] args) {
		//启动springboot的方法
		SpringApplication.run(ServerApp.class, args);
	}
	
	@Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
