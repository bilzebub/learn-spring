package learn.spring.lifecycle;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import learn.spring.lifecycle.entities.Bean1;
import learn.spring.lifecycle.entities.Bean2;
import learn.spring.lifecycle.entities.CustomBeanPostProcessor;

@Configuration
public class AppWithJavaConfig {

  @Bean
  public Bean2 bean2() {
    return new Bean2();
  }

  @Bean
  public Bean1 bean1() {
    return new Bean1();
  }

  @Bean
  public Random random() {
    System.out.println("--> creating random");
    return new SecureRandom();
  }

  @Bean
  public BeanPostProcessor beanPostProcessor() {
   return new CustomBeanPostProcessor();
  }


  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppWithJavaConfig.class);
    context.close();
  }

}
