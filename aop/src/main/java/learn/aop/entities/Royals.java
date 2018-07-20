package learn.aop.entities;

import org.springframework.stereotype.Component;

@Component
public class Royals implements Team {
    public String getName() {
        return "Kansas City Royals";
    }
}
