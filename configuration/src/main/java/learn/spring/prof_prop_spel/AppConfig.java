package learn.spring.prof_prop_spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("learn.spring.prof_prop_spel")
@PropertySource("classpath:application.properties")
@PropertySource("classpath:${db.props.path}")
public class AppConfig {

  @Autowired
  private Environment env;

  // Values are NOT beans

  @Value("I'm hard coded string")
  private String hardCodedString;

  @Value("${greeting.text}")
  private String stringFromProps;

  @Value("#{new Boolean(environment['spring.profiles.active']=='prod')}")
  private Boolean isProd;


  @Bean
  public StringWrapper hardCoded() {
    return new StringWrapper(hardCodedString);
  }

  @Bean
  public StringWrapper fromProps() {
    return new StringWrapper(stringFromProps);
  }

  @Bean
  public StringWrapper fromEnv() {
    return new StringWrapper(env.getProperty("greeting.text"));
  }

  @Bean
  public StringWrapper fromSystemEnv() {
    // it will override this property from properties file
    System.setProperty("another.property", "I'm string from system env");
    return new StringWrapper(env.getProperty("another.property"));
  }

  @Bean
  StringWrapper fromReferencedProps() {
    return new StringWrapper(env.getProperty("database.name"));
  }

  @Bean
  public StringWrapper fromSpel() {
    return new StringWrapper("Current profile is prod: " + isProd);
  }


  @Bean("profileDependent")
  @Profile("prod")
  StringWrapper prodProperty() {
    return new StringWrapper("I'm PROD property");
  }

  @Bean
  @Profile("!prod")
  StringWrapper notProdProperty() {
    return new StringWrapper("I'm NOT PROD property");
  }

  @Bean("profileDependent")
  @Profile({"dev", "default"})
  StringWrapper devProperty() {
    return new StringWrapper("I'm DEV/DEFAULT property");
  }


}
