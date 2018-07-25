package learn.spring.configuration.annotations.entities;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class StartDestroy {

    @PostConstruct
    public void init() {
        System.out.println(">> init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(">> destroy");
    }
}
