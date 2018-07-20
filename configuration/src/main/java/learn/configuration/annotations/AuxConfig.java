package learn.configuration.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;
import java.util.Random;

@Configuration
public class AuxConfig {
    @Bean
    public Random random() {
        return new SecureRandom();
    }
}
