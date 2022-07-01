package com.ironhack.midtermproject.controller.impl;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyAccountHolderDTO;
import com.ironhack.midtermproject.controller.interfaces.AccountHolderController;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.repository.accounts.AccountRepository;
import com.ironhack.midtermproject.security.CustomUserDetails;
import com.ironhack.midtermproject.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @GetMapping("/myAccounts/{accountId}/transfers/receiver")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> findMyTransfersReceiverByAccountId(@PathVariable Long accountId, @AuthenticationPrincipal  CustomUserDetails userDetails) {
        return accountHolderService.findMyTransfersReceiverByAccountId(accountId, userDetails.getUser().getId());
    }
    @GetMapping("/myAccounts/{accountId}/transfers/sender")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> findMyTransfersSenderByAccountId(@PathVariable Long accountId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountHolderService.findMyTransfersSenderByAccountId(accountId, userDetails.getUser().getId());
    }

    @PatchMapping("/myAccounts/{accountId}/transfer/accountHolder")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMoneyAccountHolder(@PathVariable Long accountId, @AuthenticationPrincipal CustomUserDetails userDetails,
                                       @RequestBody @Valid TransferSendMoneyAccountHolderDTO transferSendMoneyAccountHolderDTO) {
        accountHolderService.sendMoney(accountId, userDetails.getUser().getId(), transferSendMoneyAccountHolderDTO);
    }
}
