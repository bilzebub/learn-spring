package learn.configuration.java;

import learn.configuration.java.entities.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import java.util.Random;

@Configuration
@Import(AuxConfig.class)
public class AppConfig {

    @Bean
    public Team redSox() {
        return new RedSox();
    }

    @Bean
    public Team cubs() {
        return new Cubs();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy") // must be a singleton
    public StartDestroy startDestroy() {
        return new StartDestroy();
    }

    @Bean
    @Scope("prototype")
    public Game game(Random random) {
        BaseballGame game = new BaseballGame(redSox(), cubs());
        game.setRandom(random);
        return game;

    }
}
