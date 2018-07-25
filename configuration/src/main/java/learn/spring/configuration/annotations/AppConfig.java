package learn.spring.configuration.annotations;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import learn.spring.configuration.annotations.entities.BaseballGame;
import learn.spring.configuration.annotations.entities.Game;
import learn.spring.configuration.annotations.entities.StartDestroy;
import learn.spring.configuration.annotations.entities.Team;

@Configuration
@ComponentScan(basePackages = "learn.spring.configuration.annotations")
public class AppConfig {

    @Autowired
    private Random random;

    @Autowired @Qualifier("redSox") // autowired by type, need to qualify name
    private Team home;

    @Resource(name = "cubs") // autowired by name
    private Team away;

    @Autowired
    private StartDestroy startDestroy; // must be a singleton

    @Bean
    @Scope("prototype")
    public Game game() {
        BaseballGame game = new BaseballGame(home, away);
        return game;

    }
}
