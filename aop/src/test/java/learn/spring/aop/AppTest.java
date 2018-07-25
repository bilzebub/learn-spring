package learn.spring.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import learn.spring.aop.entities.Game;
import learn.spring.aop.entities.Team;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
public class AppTest {
  // integration tests, not actual unit tests
  @Autowired
  private Game game;

  @Autowired
  private ApplicationContext ctx;


  @Test
  void testSetHomeTeam() {
    Team home = ctx.getBean("royals", Team.class);
    game.setHomeTeam(home);
    Assertions.assertEquals(home, game.getHomeTeam());
  }

  @Test
  void testGame(){
    Team home = game.getHomeTeam();
    Team away = game.getAwayTeam();

    Team winner = game.playGame();
    Assertions.assertTrue(winner == home || winner == away);
  }
}
