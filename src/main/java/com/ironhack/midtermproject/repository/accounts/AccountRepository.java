package com.ironhack.midtermproject.repository.accounts;

import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByPrimaryOwner(AccountHolder accountHolder);

    List<Account> findByTypeAccount(TypeAccountEnum typeAccount);
}
