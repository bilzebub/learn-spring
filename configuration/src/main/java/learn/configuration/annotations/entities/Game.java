package learn.configuration.annotations.entities;

public interface Game {
    Team getHomeTeam();
    Team getAwayTeam();

    Team playGame();
}
