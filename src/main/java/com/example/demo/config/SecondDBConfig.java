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
        entityManagerFactoryRef = "secondDbEntityManagerFactory",
        transactionManagerRef = "secondDbTransactionManager",
        basePackages = {"com.example.demo.dao.seconddao"}
)
public class SecondDBConfig {
    @Bean(name = "secondDbDataSource")
    @ConfigurationProperties(prefix = "db2.datasource")
    public DataSource secondDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondDbEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("secondDbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.demo.entity")
                .persistenceUnit("db2")
                .build();
    }

    @Bean(name = "secondDbTransactionManager")
    public PlatformTransactionManager secondDbTransactionManager(
            @Qualifier("secondDbEntityManagerFactory") EntityManagerFactory secondDbEntityManagerFactory) {
        return new JpaTransactionManager(secondDbEntityManagerFactory);
    }
}
