package com.udacity.jdnd.course3.critter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource getDataSource() {
    DataSourceBuilder dsb = DataSourceBuilder.create();
    dsb.url(env.getProperty("spring.datasource.url"));
    dsb.username(env.getProperty("spring.datasource.username"));
    dsb.password(env.getProperty("spring.datasource.password"));
    return dsb.build();
  }
}
