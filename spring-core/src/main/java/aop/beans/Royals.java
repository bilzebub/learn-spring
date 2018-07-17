package aop.beans;

import org.springframework.stereotype.Component;

@Component
public class Royals implements Team {
    public String getName() {
        return "Kansas City Royals";
    }
}
