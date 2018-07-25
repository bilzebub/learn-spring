package learn.spring.jdbc;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import learn.spring.jdbc.config.AppConfig;
import learn.spring.jdbc.services.AccountService;


public class RunDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
      System.out.println("Active profiles: " + Arrays.toString(context.getEnvironment().getActiveProfiles()));
      System.out.println("Existing accounts: " + context.getBean(AccountService.class).getAccounts());

        for (String name : context.getBeanDefinitionNames()) {
          if (!name.startsWith("org.springframework"))
            System.out.println(" - " + name);
        }
    }
}
