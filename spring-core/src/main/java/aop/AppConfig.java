package aop;

import aop.beans.BaseballGame;
import aop.beans.Game;
import aop.beans.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import java.security.SecureRandom;
import java.util.Random;

import javax.annotation.Resource;

@Configuration
@ComponentScan(basePackages = "aop")
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
