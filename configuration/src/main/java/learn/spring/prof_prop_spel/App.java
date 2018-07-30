package learn.spring.prof_prop_spel;

import java.util.Arrays;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    System.out.println("Active profiles: " + Arrays.toString(context.getEnvironment().getActiveProfiles()));

    Map<String, StringWrapper> wrappers = context.getBeansOfType(StringWrapper.class);
    wrappers.entrySet().forEach(System.out::println);


  }
}
