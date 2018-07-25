package learn.spring.data;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import learn.spring.data.config.AppConfig;

public class RunDemo {
  public static void main(String[] args) {
    ApplicationContext context =
      new AnnotationConfigApplicationContext(
        AppConfig.class, AppConfig.class);
    System.out.println(context.getBeanDefinitionCount());
    for (String name : context.getBeanDefinitionNames()) {
      System.out.println(name);
    }
  }
}
