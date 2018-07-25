package learn.spring.configuration.java;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuxConfig {
    @Bean
    public Random random() {
        return new SecureRandom();
    }
}
