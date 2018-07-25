package learn.spring.configuration.annotations;

import java.util.stream.Stream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import learn.spring.configuration.annotations.entities.Game;
import learn.spring.configuration.annotations.entities.Team;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);

        Game game = context.getBean("game", Game.class);
        System.out.println(game.playGame().getName());

        System.out.println(game == context.getBean(Game.class)); // not a singleton
        System.out.println(game.getHomeTeam() == context.getBean("redSox", Team.class)); // is a singleton

        context.close();
    }
}
