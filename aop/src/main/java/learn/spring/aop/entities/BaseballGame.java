package learn.spring.aop.entities;

import java.util.Random;

import learn.spring.aop.aspects.Timed;

public class BaseballGame implements Game {

    private Team homeTeam;
    private Team awayTeam;
    private Random random;

    public BaseballGame(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }


  @Override
  public Team getHomeTeam() {
    return homeTeam;
  }

  @Override
  public void setHomeTeam(Team homeTeam) {
    this.homeTeam = homeTeam;
  }

  @Override
  public Team getAwayTeam() {
    return awayTeam;
  }

  @Override
  public void setAwayTeam(Team awayTeam) {
    this.awayTeam = awayTeam;
  }

  @Override
  public String toString() {
    return "BaseballGame{" + homeTeam.getName() + " vs. " + awayTeam.getName() + "}";
  }

  public void setRandom(Random random) {
        this.random = random;
    }

  @Timed
  public void prepare() {
    try {
      Thread.sleep(random.nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public Team playGame() {
      Team winner = random.nextBoolean() ? homeTeam : awayTeam;
      System.out.println(homeTeam.getName() + " vs. " + awayTeam.getName() + " => " + winner.getName());
      return winner;
  }
}
