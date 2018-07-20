package learn.configuration.annotations;

import learn.configuration.annotations.entities.Game;
import learn.configuration.annotations.entities.Team;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

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
