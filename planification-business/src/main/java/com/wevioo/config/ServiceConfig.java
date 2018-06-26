package com.wevioo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Dozer configuration
 * 
 * @author Jihed KAOUECH
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.wevioo.service"})
@Import(value = { DatabaseSpringDataConfig.class, EmailConfig.class })
public class ServiceConfig {

	@Bean
	public ModelMapper userMapper(){
		ModelMapper modelMapper = new ModelMapper();
		//modelMapper.addMappings(UserMap.getInstance());
		return modelMapper;
	}
	
	@Bean
	public ModelMapper operateurMapper(){
		ModelMapper modelMapper = new ModelMapper();
		//modelMapper.addMappings(UserMap.getInstance());
		return modelMapper;
	}
	
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	
}
