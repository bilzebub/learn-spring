package aop;

import aop.beans.BaseballGame;
import aop.beans.Game;
import aop.beans.Team;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/*
Join Point - public methods in Spring-managed beans
Pointcut - actual joint points that we have declared
Advice - functionality we want to apply
Aspect - combines pointcut and advice
Weaving - process of applying an aspect to our system
 */


public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Team redSox = context.getBean("redSox", Team.class);
        Team cubs = context.getBean("cubs", Team.class);
        Team royals = context.getBean("royals", Team.class);



        Game game = context.getBean(Game.class);
        game.setHomeTeam(redSox);
        game.setAwayTeam(cubs);
        game.prepare();
        Team winner = game.playGame();

        game = context.getBean(Game.class);
        game.setHomeTeam(winner);
        game.setAwayTeam(royals);
        game.prepare();
        game.playGame();


    }
}
