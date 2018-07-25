package learn.spring.aop.entities;

public interface Game {
    void setHomeTeam(Team team);
    Team getHomeTeam();
    void setAwayTeam(Team team);
    Team getAwayTeam();

    void prepare();
    Team playGame();


}
