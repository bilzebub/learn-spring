package configuration.java.beans;

public interface Game {
    Team getHomeTeam();
    Team getAwayTeam();

    Team playGame();
}
