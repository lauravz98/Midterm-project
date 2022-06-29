package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.controller.dto.AccountBalanceDTO;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.Checking;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface AdminController {

    List<Account> findAll();
    Account findAccountById(Long accountId);
    Account store(Checking checkingAccount);
    void updateBalance(Long accountId, AccountBalanceDTO accountBalanceDTO);
    void delete(Long accountId);
}
