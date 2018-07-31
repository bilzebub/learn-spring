package learn.spring.lifecycle.entities;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean1 {

  private Random random;

  public Bean1() {
    System.out.println("--> Bean1 constructor");
  }

  @Autowired
  public void setRandom(Random random) {
    System.out.println("--> Bean1 autowired setter");
    this.random = random;
  }

  @PostConstruct
  public void postConstruct() {
    System.out.println("--> Bean1 init (post construct)");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("--> Bean1 pre destroy");
  }

}
