package com.example.prog4.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@AllArgsConstructor
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
  basePackages = "com.example.prog4.repository.cnapsEntity",
  entityManagerFactoryRef = "cnapsEntityManager",
  transactionManagerRef = "cnapsTransactionManager")
public class PersistenceCnapsAutoConfiguration {
  private final Environment env;


  @Bean(name = "cnapsDataSource")
  @ConfigurationProperties(prefix="spring.second-datasource")
  public DataSource cnapsDataSource() {
    return DataSourceBuilder.create()
      .url(env.getProperty("spring.second-datasource.url")) // Replace with your actual JDBC URL
      .username(env.getProperty("spring.second-datasource.username")) // Replace with your actual username
      .password(env.getProperty("spring.second-datasource.password"))
      .build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean cnapsEntityManager() {
    LocalContainerEntityManagerFactoryBean em
      = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(cnapsDataSource());
    em.setPackagesToScan(
      new String[] { "com.example.prog4.repository.cnapsEntity" });

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto",
      env.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect",
      env.getProperty("hibernate.dialect"));
    em.setJpaPropertyMap(properties);

    return em;
  }

  @Bean
  public PlatformTransactionManager cnapsTransactionManager() {

    JpaTransactionManager transactionManager
      = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
      cnapsEntityManager().getObject());
    return transactionManager;
  }

  @Bean(initMethod = "migrate")
  public Flyway cnapsFlyway() {
    return Flyway.configure()
      .dataSource(cnapsDataSource())
      .locations("classpath:db/migration/cnaps")
      .load();
  }

}
