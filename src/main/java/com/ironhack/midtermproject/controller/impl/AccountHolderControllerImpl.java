package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyDTO;
import com.ironhack.midtermproject.controller.interfaces.AccountHolderController;
import com.ironhack.midtermproject.models.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.repository.accounts.AccountRepository;
import com.ironhack.midtermproject.security.CustomUserDetails;
import com.ironhack.midtermproject.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/myAccounts")
    @ResponseStatus(HttpStatus.OK)
    public Set<Account> findMyAccountsByAccountHolderId(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountHolderService.findMyAccountsByAccountHolderId(userDetails.getUser().getId());
    }

    @GetMapping("/myAccounts/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account findMyAccountByAccountId(@PathVariable Long accountId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountHolderService.findMyAccountByAccountId(accountId, userDetails.getUser().getId());
    }
    @GetMapping("/myAccounts/{accountId}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money findMyBalanceByAccountId(@PathVariable Long accountId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountHolderService.findMyAccountByAccountId(accountId, userDetails.getUser().getId()).getBalance();
    }

    @PatchMapping("/myAccounts/{accountId}/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMoney(@PathVariable Long accountId, @AuthenticationPrincipal CustomUserDetails userDetails,
                          @RequestBody @Valid TransferSendMoneyDTO transferSendMoneyDTO) {
        accountHolderService.sendMoney(accountId, userDetails.getUser().getId(), transferSendMoneyDTO);
    }
}
