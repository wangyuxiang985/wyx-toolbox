package com.wyx.toolbox.datasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源管理
 * @author wangyu
 */
@Configuration
public class DataSourceConfig {

    /**
     * 连接demo数据源
     */
    @Bean(name = "demoDs")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.demo")
    public DataSource demoDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * 连接test的数据源
     */
    @Bean(name = "testDs")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }
}
