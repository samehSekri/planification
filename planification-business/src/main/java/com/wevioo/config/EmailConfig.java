package com.wevioo.config;

import java.util.Properties;

import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

@Configuration
@EnableAsync
@ComponentScan(basePackages = { "com.wevioo.utility.mail" })
public class EmailConfig {

	@Autowired
	public Environment env;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(env.getProperty("wevioo.mail.host"));
		javaMailSender.setPort(env.getProperty("wevioo.mail.port", Integer.class));
		javaMailSender.setUsername(env.getProperty("wevioo.mail.username"));
		javaMailSender.setPassword(env.getProperty("wevioo.mail.password"));

		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", env.getProperty("wevioo.mail.properties.mail.smtp.auth", Boolean.class));
		properties.put("mail.smtp.starttls.enable", env.getProperty("wevioo.mail.properties.mail.smtp.starttls.enable", Boolean.class));
		properties.put("mail.debug", false);
		javaMailSender.setJavaMailProperties(properties);
		return javaMailSender;

	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
		emailTemplateResolver.setPrefix("/templates/emails/");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode("HTML5");
		emailTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
		emailTemplateResolver.setCacheable(false);
		emailTemplateResolver.setOrder(1);
		return emailTemplateResolver;
	}

	@Bean
	public TemplateEngine templateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

}
