package com.yjl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.yjl.filter.MyFilter;

@EnableZuulProxy
@EnableEurekaClient 
@SpringBootApplication
public class ApiGetawayServer extends SpringBootServletInitializer {
	@Bean
	public MyFilter getAccessFilter() {
		return new MyFilter();
	}
	
	@Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
	
	public static void main(String[] args) {
		//new SpringApplicationBuilder(ApiGetawayServer.class).web(true).run(args);
		SpringApplication.run(ApiGetawayServer.class, args);
	}
	
	
}
