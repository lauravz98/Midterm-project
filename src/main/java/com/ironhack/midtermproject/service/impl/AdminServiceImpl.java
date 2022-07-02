package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.CreditCardCreateDTO;
import com.ironhack.midtermproject.enums.TypeAccountEnum;
import com.ironhack.midtermproject.models.accounts.*;
import com.ironhack.midtermproject.models.users.ThirdParty;
import com.ironhack.midtermproject.repository.accounts.AccountRepository;
import com.ironhack.midtermproject.repository.users.AdminRepository;
import com.ironhack.midtermproject.repository.users.ThirdPartyRepository;
import com.ironhack.midtermproject.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.ironhack.midtermproject.utils.utils.convertToLocalDateViaInstant;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public Account findAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account not found. Invalid account ID"));
        return account;
    }

    public List<Account> findByTypeAccount(TypeAccountEnum typeAccount) {
        return accountRepository.findByTypeAccount(typeAccount);
    }

    public Account createCheckingAccount(Checking checkingAccount) {
        Account account;
        LocalDate now = LocalDate.now();
        LocalDate dateOfBirth = convertToLocalDateViaInstant(checkingAccount.getPrimaryOwner().getDateOfBirth());
        long years = ChronoUnit.YEARS.between(dateOfBirth, now);

        if(years < 24){
            account = new StudentChecking(checkingAccount.getPrimaryOwner(),checkingAccount.getSecondaryOwner(),
                    checkingAccount.getCreationDate(), checkingAccount.getSecretKey(), checkingAccount.getBalance());
        }
        else {
            account = new Checking(checkingAccount.getPrimaryOwner(),checkingAccount.getSecondaryOwner(),
                    checkingAccount.getCreationDate(), checkingAccount.getSecretKey(), checkingAccount.getBalance());
        }
        return accountRepository.save(account);
    }

    public Saving createSavingAccount(Saving savingAccount) {
        Saving account = new Saving(savingAccount.getPrimaryOwner(), savingAccount.getSecondaryOwner(),
                savingAccount.getCreationDate(), savingAccount.getSecretKey(), savingAccount.getBalance(),
                savingAccount.getMinimumBalance(), savingAccount.getInterestRate());
        return accountRepository.save(account);
    }
    public CreditCard createCreditCard(CreditCardCreateDTO creditCard) {
        CreditCard account = new CreditCard(creditCard.getPrimaryOwner(), creditCard.getSecondaryOwner(),
                creditCard.getCreationDate(), creditCard.getSecretKey(), creditCard.getBalance(),
                creditCard.getCreditLimit(), creditCard.getInterestRate());

        return accountRepository.save(account);
    }

    public ThirdParty createThirdParty(ThirdParty thirdParty) {
        ThirdParty thirdPartyCreate = new ThirdParty(thirdParty.getHashKey());
        return thirdPartyRepository.save(thirdPartyCreate);
    }

    public void updateBalance(Long accountId, Money balance) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account not found. Invalid account ID"));

        account.setBalance(balance);
        accountRepository.save(account);
    }

    public void delete(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new ResponseStatusException
                (HttpStatus.NOT_FOUND, "Account not found. Invalid account ID"));
        accountRepository.delete(account);

    }

}
