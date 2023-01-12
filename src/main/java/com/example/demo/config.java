package com.example.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.example.demo")
@PropertySource("classpath:application.properties")
public class config {
    @Autowired
    Environment env;

//    @Bean
//    public DataSource dataSource() {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//
//        try {
//            dataSource.setDriverClass(env.getProperty("jdbc.driver"));
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//
//        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//        dataSource.setUser(env.getProperty("jdbc.user"));
//        dataSource.setPassword(env.getProperty("jdbc.password"));
//
//        dataSource.setInitialPoolSize(convertToInt("connection.pool.initialPoolSize"));
//        dataSource.setMinPoolSize(convertToInt("connection.pool.minPoolSize"));
//        dataSource.setMaxPoolSize(convertToInt("connection.pool.maxPoolSize"));
//        dataSource.setMaxIdleTime(convertToInt("connection.pool.maxIdleTime"));
//
//        return dataSource;
//    }

//    @Bean
//    public DataSource securityDataSource() {
//        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
//
//        try {
//            securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//
//        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url.security"));
//        securityDataSource.setUser(env.getProperty("jdbc.user"));
//        securityDataSource.setPassword(env.getProperty("jdbc.password"));
//
//        securityDataSource.setInitialPoolSize(convertToInt("connection.pool.initialPoolSize"));
//        securityDataSource.setMinPoolSize(convertToInt("connection.pool.minPoolSize"));
//        securityDataSource.setMaxPoolSize(convertToInt("connection.pool.maxPoolSize"));
//        securityDataSource.setMaxIdleTime(convertToInt("connection.pool.maxIdleTime"));
//
//        return securityDataSource;
//    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    public int convertToInt(String prop) {
//        int i = Integer.parseInt(env.getProperty(prop));
//        return i;
//    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.example.demo.entity");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());
//
//        return em;
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

//    Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.ddl-auto", "create");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//
//        return properties;
//    }
}
