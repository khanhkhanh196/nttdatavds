package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "firstDbEntityManagerFactory",
        transactionManagerRef = "firstDbTransactionManager",
        basePackages = {"com.example.demo.repository"}
)
public class FirstDBConfig {
    @Primary
    @Bean(name = "firstDbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource firstDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "firstDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean firstDbEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("firstDbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.demo.entity")
                .persistenceUnit("db1")
                .build();
    }

    @Primary
    @Bean(name = "firstDbTransactionManager")
    public PlatformTransactionManager firstDbTransactionManager(
            @Qualifier("firstDbEntityManagerFactory") EntityManagerFactory firstDbEntityManagerFactory) {
        return new JpaTransactionManager(firstDbEntityManagerFactory);
    }
}
