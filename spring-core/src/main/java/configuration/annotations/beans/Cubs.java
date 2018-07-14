package configuration.annotations.beans;

import org.springframework.stereotype.Component;

@Component
public class Cubs implements Team {

    public String getName() {
        return "Chicago Cubs";
    }
}
