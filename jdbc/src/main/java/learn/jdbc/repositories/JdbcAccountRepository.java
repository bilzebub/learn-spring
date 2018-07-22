package learn.jdbc.repositories;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import learn.jdbc.entities.Account;

@Repository
@Profile({"prod", "test"})
public class JdbcAccountRepository implements AccountRepository {
    private final JdbcTemplate template;

    @Autowired
    public JdbcAccountRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Account> getAccounts() {
        String sqlTxt = "select * from account";
        return template.query(sqlTxt, accountMapper);
    }

    @Override
    public Account getAccount(Long id) {
        String sqlTxt = "select * from account where id=?";
        return template.queryForObject(sqlTxt, accountMapper, id);
    }


    @Override
    public int getNumberOfAccounts() {
        String sqlTxt = "select count(*) from account";
        return template.queryForObject(sqlTxt, Integer.class);
    }

    @Override
    public Long createAccount(BigDecimal initialBalance) {
        return new SimpleJdbcInsert(template)
          .withTableName("account")
          .usingGeneratedKeyColumns("id")
          .executeAndReturnKey(Collections.singletonMap("balance", initialBalance))
          .longValue();
    }

    @Override
    public void updateAccount(Account account) {
        String sqlTxt = "update account set balance = ? where id = ?";
        template.update(sqlTxt, account.getBalance(), account.getId());
    }

    @Override
    public boolean deleteAccount(Long id) {
        String sqlTxt = "delete from account where id=?";
        return template.update(sqlTxt, id) == 1;
    }

    private static final RowMapper<Account> accountMapper = (rs, rowNum) ->
      new Account(rs.getLong("id"), rs.getBigDecimal("balance"));

}
