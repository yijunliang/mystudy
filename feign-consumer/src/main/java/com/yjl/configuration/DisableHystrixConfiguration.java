/*package com.yjl.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import feign.Feign;
//关闭Hystrix的配置类
@Configuration
public class DisableHystrixConfiguration {
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
*/