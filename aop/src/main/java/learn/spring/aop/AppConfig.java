package learn.spring.aop;

import java.security.SecureRandom;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import learn.spring.aop.entities.BaseballGame;
import learn.spring.aop.entities.Game;
import learn.spring.aop.entities.Team;

@Configuration
@ComponentScan(basePackages = "learn.spring.aop")
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
