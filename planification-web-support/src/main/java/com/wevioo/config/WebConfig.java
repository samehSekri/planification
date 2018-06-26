package com.wevioo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wevioo.utility.MessageUtil;

@Configuration
@ComponentScan(basePackages = { "com.wevioo.controller" })
@Import(value = { ServiceConfig.class, SecurityConfig.class, MessageUtil.class})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowCredentials(true)
		.allowedHeaders("*")
		.allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
		.allowedOrigins("*");
	}
}
