package com.example.prog4.config;

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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@AllArgsConstructor
@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = "com.example.prog4.repository.entity",
  entityManagerFactoryRef = "employeeEntityManager",
  transactionManagerRef = "employeeTransactionManager")
public class PersistenceEmployeeAutoConfiguration {
  private final Environment env;

  @Primary
  @Bean(name = "employeeDataSource")
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource employeeDataSource() {
    return DataSourceBuilder.create()
      .url(env.getProperty("spring.datasource.url")) // Replace with your actual JDBC URL
      .username(env.getProperty("spring.datasource.username")) // Replace with your actual username
      .password(env.getProperty("spring.datasource.password"))
      .build();
  }

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean employeeEntityManager() {
    LocalContainerEntityManagerFactoryBean em
      = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(employeeDataSource());
    em.setPackagesToScan(
      new String[]{"com.example.prog4.repository.cnapsEntity"});

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

  @Primary
  @Bean
  public PlatformTransactionManager employeeTransactionManager() {

    JpaTransactionManager transactionManager
      = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
      employeeEntityManager().getObject());
    return transactionManager;
  }

  @Bean(initMethod = "migrate")
  public Flyway employeeFlyway() {
    return Flyway.configure()
      .dataSource(employeeDataSource())
      .locations("classpath:db/migration/employee")
      .load();
  }

}
