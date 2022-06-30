package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByPrimaryOwner(AccountHolder accountHolder);

    List<Account> findByTypeAccount(TypeAccountEnum typeAccount);
}
