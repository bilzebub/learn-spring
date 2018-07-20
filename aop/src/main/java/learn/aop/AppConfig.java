package learn.aop;

import learn.aop.entities.BaseballGame;
import learn.aop.entities.Game;
import learn.aop.entities.Team;

import org.springframework.context.annotation.*;

import java.security.SecureRandom;
import java.util.Random;

import javax.annotation.Resource;

@Configuration
@ComponentScan(basePackages = "learn.aop")
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Random random() {
        return new SecureRandom();
    }

    @Resource
    private Team redSox;

    @Resource
    private Team cubs;

    @Resource
    private Team royals;

    @Bean
    @Scope("prototype")
    public Game game() {
        BaseballGame game = new BaseballGame(redSox, cubs);
        game.setRandom(random());
        return game;
    }
}
