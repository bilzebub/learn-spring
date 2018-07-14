package aop.beans;

public interface Game {
    Team getHomeTeam();
    Team getAwayTeam();

    Team playGame();
}
