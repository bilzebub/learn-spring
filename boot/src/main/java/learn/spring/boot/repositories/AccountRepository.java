package learn.spring.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import learn.spring.boot.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
