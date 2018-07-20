package learn.configuration.annotations.entities;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseballGame implements Game {
    private final Team homeTeam;
    private final Team awayTeam;
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

    @Autowired
    public void setRandom(Random random) {
        this.random = random;
    }

    public Team playGame() {
        return random.nextBoolean() ? homeTeam : awayTeam;
    }
}
