package learn.configuration.annotations;

import learn.configuration.annotations.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "learn.configuration.annotations")
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
