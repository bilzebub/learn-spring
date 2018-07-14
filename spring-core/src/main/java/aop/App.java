package aop;

import aop.beans.Game;
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

        Game game = context.getBean("game", Game.class);
        System.out.println(game.playGame().getName());

    }
}
