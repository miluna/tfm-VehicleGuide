package com.miluna.springvehicleguide.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@PropertySource("classpath:application.yml")
@Configuration
public class CustomDataSource {

    @Value("${spring.datasource.url}") private String jdbcUrl;
    @Value("${spring.datasource.hikari.username}") private String username;
    @Value("${spring.datasource.hikari.password}") private String password;
    @Value("${spring.datasource.driver-class-name}") private String driver;

    @Bean(value = "DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(jdbcUrl)
                .driverClassName(driver)
                .build();
    }

    @Bean(value = "JdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(this.dataSource());
    }
}
