package com.sensor.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static com.sensor.constant.ApplicationConstant.PATH_TO_PACKAGE_TO_SCAN;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.sensor.dao")
@PropertySource("classpath:application.properties")
public class HibernateConfiguration {

    @Value(value = "${postgres.url}")
    private String url;
    @Value(value = "${postgres.user}")
    private String user;
    @Value(value = "${postgres.driver}")
    private String driver;
    @Value(value = "${postgres.password}")
    private String password;
    @Value(value = "${hibernate.dialect}")
    private String dialect;
    @Value(value = "${hibernate.show_sql}")
    private String showSql;
    @Value(value = "${hibernate.schema}")
    private String schema;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PATH_TO_PACKAGE_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(Environment.DIALECT, dialect);
        hibernateProperties.setProperty(Environment.SHOW_SQL, showSql);
        hibernateProperties.setProperty(Environment.DEFAULT_SCHEMA, schema);
        hibernateProperties.setProperty("hibernate.search.default.directory_provider", "filesystem");
        hibernateProperties.setProperty("hibernate.search.default.indexBase", "/indexes");
        return hibernateProperties;
    }
}
