package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.controller.dto.AccountBalanceDTO;
import com.ironhack.midtermproject.controller.interfaces.AdminController;
import com.ironhack.midtermproject.models.Account;
import com.ironhack.midtermproject.models.Checking;
import com.ironhack.midtermproject.models.CreditCard;
import com.ironhack.midtermproject.models.Saving;
import com.ironhack.midtermproject.repository.AccountRepository;
import com.ironhack.midtermproject.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminControllerImpl implements AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    @GetMapping("/accounts/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable Long accountId) {
        return adminService.findAccountById(accountId);
    }
    @PostMapping("/accounts/checking") // Created checking account
    @ResponseStatus(HttpStatus.CREATED)
    public Account store(@RequestBody @Valid Checking checkingAccount){
        return adminService.createCheckingAccount(checkingAccount);
    }
    @PostMapping("/accounts/saving") // Created saving
    @ResponseStatus(HttpStatus.CREATED)
    public Account store(@RequestBody @Valid Saving savingAccount){
        return adminService.createSavingAccount(savingAccount);
    }
    @PostMapping("/accounts/creditCard") // Created saving
    @ResponseStatus(HttpStatus.CREATED)
    public Account store(CreditCard creditCard) {
        return adminService.createCreditCard(creditCard);
    }

    @PatchMapping("/accounts/{accountId}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@PathVariable Long accountId, @RequestBody @Valid AccountBalanceDTO accountBalanceDTO) {
        adminService.updateBalance(accountId, accountBalanceDTO.getBalance());
    }

    @DeleteMapping("/accounts/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long accountId) {
        adminService.delete(accountId);
    }
}
