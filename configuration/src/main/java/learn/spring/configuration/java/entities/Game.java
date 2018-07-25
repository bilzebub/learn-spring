package learn.spring.configuration.java.entities;

public interface Game {
    Team getHomeTeam();
    Team getAwayTeam();

    Team playGame();
}
