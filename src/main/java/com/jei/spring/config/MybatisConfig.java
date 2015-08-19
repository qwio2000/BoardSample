package com.jei.spring.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


@Configuration
@MapperScan("com.jei.spring")
public class MybatisConfig {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		/**
		 * 리소스에 정의되어있는 mapper 읽어드릴 xml 위치설정
		 */
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/jei/spring/mybatis/mapper/*.xml"));
		/**
		 * alias 검색할 패키지 위치
		 */
		sqlSessionFactoryBean.setTypeAliasesPackage("com.jei.spring.domain");
		
		return sqlSessionFactoryBean.getObject();
	}
}
