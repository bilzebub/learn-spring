package learn.spring.jdbc.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import learn.spring.jdbc.entities.Account;

@Repository
@Profile("dev")
public class InMemoryAccountRepository implements AccountRepository {
    private static Map<Long, Account> accountMap = new ConcurrentHashMap<>();
    private static Long nextId = 1L;

    static {
        accountMap.put(nextId, new Account(nextId++, new BigDecimal("100.0")));
        accountMap.put(nextId, new Account(nextId++, new BigDecimal("120.0")));
        accountMap.put(nextId, new Account(nextId++, new BigDecimal("150.0")));
    }

    @Override
    public List<Account> getAccounts() {
        return new ArrayList<>(accountMap.values());
    }

    @Override
    public Account getAccount(Long id) {
        return accountMap.get(id);
    }

    @Override
    public int getNumberOfAccounts() {
        return accountMap.size();
    }

    @Override
    public Long createAccount(BigDecimal initialBalance) {
        long id = nextId++;
        accountMap.put(id, new Account(id, initialBalance));
        return id;
    }

    @Override
    public boolean deleteAccount(Long id) {
        return accountMap.remove(id) != null;
    }

    @Override
    public void updateAccount(Account account) {
        accountMap.put(account.getId(), account);
    }
}
