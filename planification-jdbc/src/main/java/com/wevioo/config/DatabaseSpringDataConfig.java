package com.wevioo.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * SpringData Configuration
 * 
 * @author Jihed Kaouech
 *
 */
@Configuration
@EnableJpaRepositories("com.wevioo.dao")
@Import(value = { DatabaseConfig.class })
public class DatabaseSpringDataConfig {

	private static final String MODEL_PACKAGE = "com.wevioo.model";

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Environment env;

	/**
	 * Hinernate entity manager bean
	 * 
	 * @return EntityManagerFactory
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory() {

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(MODEL_PACKAGE);
		factory.setDataSource(dataSource);
		factory.setJpaDialect(new HibernateJpaDialect());
		factory.setJpaProperties(getHibernateProperties());
		factory.afterPropertiesSet();
		
		return factory.getObject();

	}

	/**
	 * Transaction manager initialization bean
	 * 
	 * @return JpaTransactionManager
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		JpaDialect jpaDialect = new HibernateJpaDialect();
		txManager.setJpaDialect(jpaDialect);
		return txManager;

	}

	/**
	 * Hibernate properties definition
	 * 
	 * @return properties for hibernate
	 */
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", env.getProperty("wevioo.database.hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("wevioo.database.hibernate.show_sql", "false"));
		properties.setProperty("hibernate.format_sql", env.getProperty("wevioo.database.hibernate.format_sql", "false"));
		properties.setProperty("hibernate.generate_statistics", env.getProperty("wevioo.database.hibernate.generate_statistics", "false"));
		properties.setProperty("hibernate.use_sql_comments", env.getProperty("wevioo.database.hibernate.use_sql_comments", "false"));
		
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("wevioo.database.hibernate.hbm2ddl_auto", "validate"));
		return properties;
	}

}
