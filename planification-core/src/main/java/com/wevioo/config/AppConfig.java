package com.wevioo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Application configuration
 * 
 * @author Jihed KAOUECH
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.wevioo.aspect" })
@PropertySource({ "classpath:application.properties" })
public class AppConfig {

	/**
	 * Resolves ${...} placeholders within bean definition property values and @Value annotations in XML
	 * <context:property-placeholder>
	 * 
	 * @return the PropertySourcesPlaceholderConfigurer
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		configurer.setLocation(new ClassPathResource("application.properties"));
		return configurer;
	}

}