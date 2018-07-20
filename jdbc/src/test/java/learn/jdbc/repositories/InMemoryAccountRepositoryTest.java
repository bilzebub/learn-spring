package learn.jdbc.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import learn.jdbc.AppConfig;
import learn.jdbc.entities.Account;

@SuppressWarnings("Duplicates")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
// @Transactional
@ActiveProfiles("dev")
public class InMemoryAccountRepositoryTest {

  @Autowired
  private AccountRepository repository;

  @Test
  public void testGetAccounts() throws Exception {
    List<Account> accounts = repository.getAccounts();
    assertEquals(3, accounts.size());
  }

  @Test
  public void testGetAccount() throws Exception {
    Account account = repository.getAccount(1L);
    assertEquals(1L, account.getId().longValue());
    assertEquals(new BigDecimal("100.0"), account.getBalance());
  }

  @Test
  public void testGetNumberOfAccounts() throws Exception {
    assertEquals(3, repository.getNumberOfAccounts());
  }

  @Test
  public void testCreateAccount() throws Exception {
    // Make a new account and add it to DB
    Long id = repository.createAccount(new BigDecimal("250.0"));
    Assertions.assertNotNull(id);

    // Retrieve the new account and check its properties
    Account account = repository.getAccount(id);
    assertEquals(id, account.getId());
    assertEquals(new BigDecimal("250.0"), account.getBalance());
    // Delete the account
    repository.deleteAccount(id);
  }

  @Test
  public void testUpdateAccount() throws Exception {
    Account account = repository.getAccount(1L);
    BigDecimal current = account.getBalance();
    BigDecimal amount = new BigDecimal("50.0");
    account.setBalance(current.add(amount));
    repository.updateAccount(account);

    Account again = repository.getAccount(1L);
    assertEquals(current.add(amount), again.getBalance());
  }

  @Test
  public void testDeleteAccount() throws Exception {
    // Get the number of accounts before deleting
    int beforeCount = repository.getNumberOfAccounts();
    Account account = repository.getAccount(3L);

    // Delete an account and check that num is one less
    assertTrue(repository.deleteAccount(3L));
    int afterCount = repository.getNumberOfAccounts();
    assertEquals(beforeCount - 1, afterCount);

    // Re-add the deleted account
    repository.createAccount(account.getBalance());
  }
}