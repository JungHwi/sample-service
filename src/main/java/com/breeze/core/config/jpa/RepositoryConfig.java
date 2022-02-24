package com.breeze.core.config.jpa;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = {"com.breeze.**.repository"})
public class RepositoryConfig {

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(EntityUtil.generateDDL(commonJpaProperty()));
        jpaVendorAdapter.setShowSql(EntityUtil.showSQL(commonJpaProperty()));

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(RepositoryConfig.class.getPackage().getName(), "com.breeze.**");

        commonJpaProperty().getProperties().put("hibernate.physical_naming_strategy", "com.breeze.core.config.jpa.JpaPhysicalNamingStrategy");
        factoryBean.setJpaProperties(commonJpaProperty().getProperties());

        return factoryBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    JpaProperty commonJpaProperty(){
        return new JpaProperty();
    }

    @Bean("routingDataSource")
    public DataSource routingDataSource() {
        ReplicationRoutingDataSource replicationRoutingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new LinkedHashMap<>();
        dataSourceMap.put("master", writeDataSource());
        dataSourceMap.put("slave", readDataSource());

        replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
        replicationRoutingDataSource.setDefaultTargetDataSource(writeDataSource());

        return replicationRoutingDataSource;
    }

    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.write")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.read")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }
}
