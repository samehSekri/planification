package com.wevioo.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DataBase Configuration
 * 
 * @author Jihed KAOUECH
 *
 */
@Configuration
@EnableTransactionManagement
@Import(value = { AppConfig.class })
public class DatabaseConfig {

	@Autowired
	private Environment env;

	/**
	 * Definition of the {@link DataSource}
	 * 
	 * @return the dataSource
	 */
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("wevioo.database.driver"));
		dataSource.setUrl(env.getProperty("wevioo.database.url"));
		dataSource.setUsername(env.getProperty("wevioo.database.username"));
		dataSource.setPassword(env.getProperty("wevioo.database.password"));
		dataSource.setInitialSize(env.getProperty("wevioo.database.initialSize", Integer.class, 0));
		dataSource.setMaxActive(env.getProperty("wevioo.database.maxActive", Integer.class, 10));
		dataSource.setRemoveAbandoned(env.getProperty("wevioo.database.removeAbandoned", Boolean.class, false));
		dataSource.setRemoveAbandonedTimeout(env.getProperty("wevioo.database.removeAbandonedTimeout", Integer.class, 300));
		return dataSource;
	}


}
