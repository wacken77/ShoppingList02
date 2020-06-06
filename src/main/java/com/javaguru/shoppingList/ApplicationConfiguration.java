package com.javaguru.shoppingList;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.javaguru.shoppingList")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class ApplicationConfiguration {
        @Value("${jdbc.url}")
        private String jdbcUrl;

        @Value("${driverClass}")
        private String driverClass;

        @Value("${database.user.name}")
        private String userName;

        @Value("${database.user.password}")
        private String password;

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
                return new PropertySourcesPlaceholderConfigurer();
        }

        @Bean
        public DataSource dataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl(jdbcUrl);
                dataSource.setDriverClassName(driverClass);
                dataSource.setUsername(userName);
                dataSource.setPassword(password);
                return dataSource;
        }

        @Bean public JdbcTemplate jdbcTemplate() {
                return new JdbcTemplate(dataSource());
        }

        @Bean
        public Properties hibernateProperties(
                @Value("org.hibernate.dialect.MySQLDialect") String dialect,
                @Value("true") boolean showSql,
                @Value("true") boolean formatSql,
                @Value("validate") String hbm2ddl) {

                Properties properties = new Properties();
                properties.put("hibernate.dialect", dialect);
                properties.put("hibernate.show_sql", showSql);
                properties.put("hibernate.format_sql", formatSql);
                properties.put("hibernate.hbm2ddl.auto", hbm2ddl);

                return properties;
        }

        @Bean
        public SessionFactory sessionFactory(DataSource dataSource,
                                             @Value("com.javaguru.shoppingList") String packagesToScan,
                                             Properties hibernateProperties) throws Exception {
                LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
                sessionFactoryBean.setDataSource(dataSource);
                sessionFactoryBean.setPackagesToScan(packagesToScan);
                sessionFactoryBean.setHibernateProperties(hibernateProperties);
                sessionFactoryBean.afterPropertiesSet();
                return sessionFactoryBean.getObject();
        }

        @Bean
        public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
                return new HibernateTransactionManager(sessionFactory);
        }
}
