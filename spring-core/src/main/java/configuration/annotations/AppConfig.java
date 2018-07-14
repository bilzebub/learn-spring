package configuration.annotations;

import configuration.annotations.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "configuration.annotations")
public class AppConfig {

    @Autowired
    private Random random;

    @Autowired @Qualifier("redSox") // autowired by type, need to qualify name
    private Team redSox;

    @Resource // autowired by name
    private Team cubs;

    @Autowired
    private StartDestroy startDestroy; // must be a singleton

    @Bean
    @Scope("prototype")
    public Game game() {
        BaseballGame game = new BaseballGame(redSox, cubs);
        game.setRandom(random);
        return game;

    }
}
