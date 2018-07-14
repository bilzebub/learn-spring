package configuration.annotations.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Random;

public class BaseballGame implements Game {
    private final Team homeTeam;
    private Team awayTeam;
    private Random random;

    public BaseballGame(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public final Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Team playGame() {
        return random.nextBoolean() ? homeTeam : awayTeam;
    }
}
