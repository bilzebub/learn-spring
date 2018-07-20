package learn.jdbc.repositories;

import learn.jdbc.entities.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository {
    List<Account> getAccounts();

    Account getAccount(Long id);

    int getNumberOfAccounts();

    Long createAccount(BigDecimal initialBalance);

    boolean deleteAccount(Long id);

    void updateAccount(Account account);
}
