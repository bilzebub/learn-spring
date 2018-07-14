package configuration.annotations.beans;

import org.springframework.stereotype.Component;

@Component
public class RedSox implements Team {

    public String getName() {
        return "Boston Red Sox";
    }
}
