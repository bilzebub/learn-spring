package learn.spring.lifecycle.entities;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean2 {

  private Bean1 bean1;

  public Bean2() {
    System.out.println("--> Bean2 constructor");
  }

  @Autowired
  public void setBean1(Bean1 bean1) {
    System.out.println("--> Bean2 autowired setter");
    this.bean1 = bean1;
  }

  @PostConstruct
  public void postConstruct() {
    System.out.println("--> Bean2 init (post construct)");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("--> Bean2 pre destroy");
  }

}
