package configuration.java.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class StartDestroy {

    public void init() {
        System.out.println(">> init");
    }

    public void destroy() {
        System.out.println(">> destroy");
    }
}
