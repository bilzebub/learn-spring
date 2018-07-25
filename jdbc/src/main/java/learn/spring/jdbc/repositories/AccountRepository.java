package learn.spring.jdbc.repositories;

import java.math.BigDecimal;
import java.util.List;

import learn.spring.jdbc.entities.Account;

public interface AccountRepository {
    List<Account> getAccounts();

    Account getAccount(Long id);

    int getNumberOfAccounts();

    Long createAccount(BigDecimal initialBalance);

    boolean deleteAccount(Long id);

    void updateAccount(Account account);
}
