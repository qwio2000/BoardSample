package com.jei.spring.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import(PropertySourcesPlaceHolderConfig.class)
public class DataSourceConfig {
	
	@Value("${db.jdbcUrl}")
	private String databaseUrl;
	
	@Value("${db.driver}")
	private String databaseDriver;
	
	@Value("${db.user}")
	private String databaseUser;
	
	@Value("${db.password}")
	private String databasePassword;
	
	
	@Bean(initMethod = "createDataSource", destroyMethod = "close")
	public BasicDataSource getDataSource() {
		final BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(databaseDriver);
		basicDataSource.setUsername(databaseUser);
		basicDataSource.setPassword(databasePassword);
		basicDataSource.setUrl(databaseUrl);
		return basicDataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}
}
