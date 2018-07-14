package aop.beans;

import org.springframework.stereotype.Component;

@Component
public class RedSox implements Team {

    public String getName() {
        return "Boston Red Sox";
    }
}
