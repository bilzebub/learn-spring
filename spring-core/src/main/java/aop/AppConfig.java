package aop;

import aop.beans.BaseballGame;
import aop.beans.Game;
import aop.beans.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import java.security.SecureRandom;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "aop")
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Random random() {
        return new SecureRandom();
    }

    @Autowired @Qualifier("redSox")
    private Team home;

    @Autowired @Qualifier("cubs")
    private Team away;

    @Bean
    @Scope("prototype")
    public Game game() {
        BaseballGame game = new BaseballGame(home, away);
        game.setRandom(random());
        return game;

    }
}
