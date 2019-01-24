package com.companyxxx.projectname.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * @author: luzjg
 * @date: 2019-01-24
 * @description: spring-data-jpa 配置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        //jpa 配置>repository位置,实体管理Factory配置,事务管理配置
        basePackages = {"com.companyxxx.projectname.repository.primary"},
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary")
public class PrimaryConfig {
    @Autowired
    @Qualifier("primaryDataSource")
    DataSource primaryDataSource;

    // 自定义EntityManagerFactory
    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .packages("com.companyxxx.projectname.domain.primary") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    //自定义EntityManager
    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    //自定义TransactionManager
    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryPrimary(builder).getObject());
        return transactionManager;
    }
}
