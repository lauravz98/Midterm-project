package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.AccountBalanceDTO;
import com.ironhack.midtermproject.controller.dto.CreditCardCreateDTO;
import com.ironhack.midtermproject.controller.interfaces.AdminController;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.accounts.Checking;
import com.ironhack.midtermproject.models.accounts.Saving;
import com.ironhack.midtermproject.models.users.ThirdParty;
import com.ironhack.midtermproject.repository.transfers.TransferRepository;
import com.ironhack.midtermproject.repository.accounts.AccountRepository;
import com.ironhack.midtermproject.repository.users.ThirdPartyRepository;
import com.ironhack.midtermproject.service.interfaces.AdminService;
import com.ironhack.midtermproject.service.interfaces.ThirdPartyService;
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

    @Autowired
    private TransferRepository transferRepository;


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
    @GetMapping("/accounts/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account findAccountById(@PathVariable Long accountId) {
        return adminService.findAccountById(accountId);
    }
    @GetMapping("/accounts/balance/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Money getBalanceByAccountId(@PathVariable Long accountId) {
        Account account = adminService.findAccountById(accountId);
        return account.getBalance();
    }
    @GetMapping("/accounts/typeAccount/{typeAccount}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findByTypeAccount(@PathVariable TypeAccountEnum typeAccount) {
        return adminService.findByTypeAccount(typeAccount);
    }
    @GetMapping("/accounts/transfers")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> findAllTransfer() {
        return transferRepository.findAll();
    }

    @PostMapping("/accounts/checking") // Created checking account
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestBody @Valid Checking checkingAccount){
        return adminService.createCheckingAccount(checkingAccount);
    }
    @PostMapping("/accounts/saving") // Created saving
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestBody @Valid Saving savingAccount){
        return adminService.createSavingAccount(savingAccount);
    }
    @PostMapping("/accounts/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createNewAccount(@RequestBody @Valid CreditCardCreateDTO creditCard) {
        return adminService.createCreditCard(creditCard);
    }
    @PostMapping("/thirdParty/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody @Valid ThirdParty thirdParty) {
        return adminService.createThirdParty(thirdParty);
    }

    @PatchMapping("/accounts/{accountId}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalanceByAccountId(@PathVariable Long accountId, @RequestBody @Valid AccountBalanceDTO accountBalanceDTO) {
        adminService.updateBalance(accountId, accountBalanceDTO.getBalance());
    }

    @DeleteMapping("/accounts/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long accountId) {
        adminService.delete(accountId);
    }
}
