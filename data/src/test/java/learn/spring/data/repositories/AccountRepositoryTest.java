package learn.spring.data.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import learn.spring.data.config.AppConfig;
import learn.spring.data.entities.Account;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void testGetAccounts() throws Exception {
        List<Account> accounts = repository.findAll();
        assertThat(accounts.size(), is(3));
    }

    @Test
    public void testGetAccount() throws Exception {
        Account account = repository.getOne(1L);
        assertThat(account.getId(), is(1L));
        assertThat(new BigDecimal("100.0"),
                is(closeTo(account.getBalance(), new BigDecimal("0.01"))));
    }

    @Test
    public void testGetNumberOfAccounts() throws Exception {
        assertThat(repository.count(), is(3L));
    }

    @Test
    public void testCreateAccount() throws Exception {
        Account account = new Account(99L, new BigDecimal("250.00"));
        repository.save(account);
        Long id = account.getId();
        assertThat(id, is(notNullValue()));

        Account again = repository.getOne(id);
        assertThat(again.getId(), is(id));
        assertThat(again.getBalance(), is(closeTo(new BigDecimal("250.0"),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account = repository.getOne(1L);
        BigDecimal current = account.getBalance();
        BigDecimal amount = new BigDecimal("50.0");
        account.setBalance(current.add(amount));
        repository.save(account);

        Account again = repository.getOne(1L);
        assertThat(again.getBalance(), is(closeTo(current.add(amount),
                new BigDecimal("0.01"))));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        repository.deleteAll();
        assertThat(repository.count(), is(0L));
    }

    @Test
    public void testAccountsBalanceGTE() throws Exception {
        List<Account> accounts = repository.findAccountsByBalanceGreaterThanEqual(
                new BigDecimal("100.0"));
        assertThat(accounts.size(), is(3));
    }
}