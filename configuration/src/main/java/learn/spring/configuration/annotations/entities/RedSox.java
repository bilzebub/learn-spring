package learn.spring.configuration.annotations.entities;

import org.springframework.stereotype.Component;

@Component
public class RedSox implements Team {

    public String getName() {
        return "Boston Red Sox";
    }
}
