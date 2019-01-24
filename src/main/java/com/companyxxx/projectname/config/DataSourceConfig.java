package com.companyxxx.projectname.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author: luzj
 * @date: 2019-01-24
 * @description: DataSource配置
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.primary")
    public DataSourceProperties firstProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties("app.datasource.primary")
    public DataSource primaryDataSource() {
        return firstProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("app.datasource.secondary")
    public DataSourceProperties secondaryProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties("app.datasource.secondary")
    public DataSource secondaryDataSource() {
        return secondaryProperties().initializeDataSourceBuilder().build();
    }


}
