package learn.jpa.services;

import learn.jpa.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AccountServiceTest {
    @Autowired
    private AccountService service;

    @Test
    public void testDeposit() throws Exception {
        BigDecimal start = service.getBalance(1L);
        BigDecimal amount = new BigDecimal("50.0");
        service.deposit(1L, amount);
        BigDecimal finish = start.add(amount);

        assertThat(finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testWithdraw() throws Exception {
        BigDecimal start = service.getBalance(1L);
        BigDecimal amount = new BigDecimal("50.0");
        service.withdraw(1L, amount);
        BigDecimal finish = start.subtract(amount);

        assertThat(finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testTransfer() throws Exception {
        BigDecimal acct1start = service.getBalance(1L);
        BigDecimal acct2start = service.getBalance(2L);

        BigDecimal amount = new BigDecimal("50.0");
        service.transfer(1L, 2L, amount);

        BigDecimal acct1finish = acct1start.subtract(amount);
        BigDecimal acct2finish = acct2start.add(amount);

        assertThat(acct1finish, is(closeTo(service.getBalance(1L),
                new BigDecimal("0.01"))));
        assertThat(acct2finish, is(closeTo(service.getBalance(2L),
                new BigDecimal("0.01"))));
    }
}