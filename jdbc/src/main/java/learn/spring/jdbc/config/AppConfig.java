package learn.spring.jdbc.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "learn.spring.jdbc")
@PropertySource("classpath:prod.properties")
@EnableTransactionManagement
public class AppConfig {

  @Autowired // содержит пропертис из @PropertySource
  private Environment env;

  /**
    нужен для управлениями транзакциями при обращении в БД, использунтся аннотацией @Transactional
   */
  @Profile({"test", "prod"})
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Profile("test")
  @Bean(name = "dataSource")
  public DataSource dataSourceForTest() {
    System.out.println("Creating data source for test");
    return new EmbeddedDatabaseBuilder()
      .generateUniqueName(true)
      .setType(EmbeddedDatabaseType.H2)
      .setScriptEncoding("UTF-8")
      .ignoreFailedDrops(true)
      .addScript("schema.sql")
      .addScripts("data.sql")
      .build();
  }

  @Profile("prod")
  @Bean(name = "dataSource")
  public DataSource dataSourceForProd() {
    System.out.println("Creating data source for prod");
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty("db.driver"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setUsername(env.getProperty("db.user"));
    dataSource.setPassword(env.getProperty("db.pass"));
    return dataSource;
  }

}
