package learn.jdbc.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import learn.jdbc.AppConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
@ActiveProfiles("test")
public class AccountServiceTest {
  @Autowired
  private AccountService service;

  @Test
  public void testDeposit() throws Exception {
    BigDecimal start = service.getBalance(1L);
    BigDecimal amount = new BigDecimal("50.0");
    service.deposit(1L, amount);
    BigDecimal finish = start.add(amount);

    assertEquals(service.getBalance(1L), finish);
  }

  @Test
  public void testWithdraw() throws Exception {
    BigDecimal start = service.getBalance(1L);
    BigDecimal amount = new BigDecimal("50.0");
    service.withdraw(1L, amount);
    BigDecimal finish = start.subtract(amount);

    assertEquals(service.getBalance(1L), finish);
  }

  @Test
  public void testTransfer() throws Exception {
    BigDecimal acct1start = service.getBalance(1L);
    BigDecimal acct2start = service.getBalance(2L);

    BigDecimal amount = new BigDecimal("50.0");
    service.transfer(1L, 2L, amount);

    BigDecimal acct1finish = acct1start.subtract(amount);
    BigDecimal acct2finish = acct2start.add(amount);

    assertEquals(service.getBalance(1L), acct1finish);
    assertEquals(service.getBalance(2L), acct2finish);
  }
}