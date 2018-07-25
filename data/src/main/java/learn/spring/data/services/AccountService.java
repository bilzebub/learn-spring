package learn.spring.data.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import learn.spring.data.entities.Account;
import learn.spring.data.repositories.AccountRepository;

@Service
@Transactional
public class AccountService {

  @Autowired
  private AccountRepository repository;

  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public BigDecimal getBalance(Long id) {
    return repository.getOne(id).getBalance();
  }

  public BigDecimal deposit(Long id, BigDecimal amount) {
    Account account = repository.getOne(id);
    BigDecimal newBalance = account.getBalance().add(amount);
    account.setBalance(newBalance);
    repository.save(account);
    return newBalance;
  }

  public BigDecimal withdraw(Long id, BigDecimal amount) {
    return deposit(id, amount.negate());
  }

  public void transfer(Long fromId, Long toId, BigDecimal amount) {
    withdraw(fromId, amount);
    deposit(toId, amount);
  }
}
