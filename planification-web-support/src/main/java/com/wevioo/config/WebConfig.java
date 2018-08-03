package com.wevioo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.wevioo.utility.MessageUtil;

@Configuration
@ComponentScan(basePackages = { "com.wevioo.controller" })
@Import(value = { ServiceConfig.class, SecurityConfig.class, MessageUtil.class })
public class WebConfig extends WebMvcConfigurerAdapter {
	public static final int UPLOAD_SIZE = 10000000;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowCredentials(true).allowedHeaders("*")
				.allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE").allowedOrigins("*");
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(UPLOAD_SIZE);
		return new CommonsMultipartResolver();
	}
}
