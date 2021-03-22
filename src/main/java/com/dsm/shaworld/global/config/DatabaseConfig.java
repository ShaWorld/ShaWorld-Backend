package com.dsm.shaworld.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
class DatabaseConfig {
    @Value("${DATABASE_DRIVER:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;
    @Value("${DATABASE_URL:jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC}")
    private String url;
    @Value("${DATABASE_USERNAME:root}")
    private String username;
    @Value("${DATABASE_PASSWORD:dhkstj3209}")
    private String password;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}