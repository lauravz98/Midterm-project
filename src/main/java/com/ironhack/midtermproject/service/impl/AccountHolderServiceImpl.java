package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyDTO;
import com.ironhack.midtermproject.models.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.users.AccountHolder;
import com.ironhack.midtermproject.repository.users.AccountHolderRepository;
import com.ironhack.midtermproject.repository.TransferRepository;
import com.ironhack.midtermproject.repository.accounts.AccountRepository;
import com.ironhack.midtermproject.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private TransferRepository transferRepository;
    public Set<Account> findMyAccountsByAccountHolderId(Long id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account Holder not found. Invalid account holder ID"));

        List<Account> accountsList = accountRepository.findByPrimaryOwner(accountHolder);
        Set<Account> accounts = new HashSet<>(accountsList);
        return accounts;
    }

    public Account findMyAccountByAccountId(Long accountId, long id) {
        Account accountRequest = accountRepository.findById(accountId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Account not found. Invalid account ID: "+ accountId));
        Set<Account> myAccounts = findMyAccountsByAccountHolderId(id);
        for(Account myAccount: myAccounts){
            if(myAccount.getAccountId() == accountRequest.getAccountId()){
                return myAccount;
            }
        }
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                "You are not allowed to access or use this account. Invalid account ID");
    }

    public void sendMoney(Long accountId, long id, TransferSendMoneyDTO transferSendMoneyDTO) {
        Account accountSender = findMyAccountByAccountId(accountId, id);
        String nameSender = accountHolderRepository.findById(id).get().getName();
        Account accountReceiver = accountRepository.findById(transferSendMoneyDTO.getAccountReceiverId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Account Receiver not found. Invalid account ID"));

        Transfer transfer;
        if(accountReceiver.getSecondaryOwner()!= null){
            if(accountReceiver.getPrimaryOwner().getName().equals(transferSendMoneyDTO.getNameReceiver())
                    || accountReceiver.getSecondaryOwner().getName().equals(transferSendMoneyDTO.getNameReceiver())) {
                Money newBalanceSender = new Money(accountSender.getBalance().decreaseAmount(transferSendMoneyDTO.getAmountMoney()));
                accountSender.setBalance(newBalanceSender);
                Money newBalanceReceiver = new Money(accountReceiver.getBalance().increaseAmount(transferSendMoneyDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceReceiver);
                transfer = new Transfer(accountSender, accountReceiver, transferSendMoneyDTO.getAmountMoney(),
                        nameSender,transferSendMoneyDTO.getNameReceiver());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Transfer receiver's name is invalid." +
                                "The name provided doesn't match the owners of the account number given.");
            }
        }
        else{
            if(accountReceiver.getPrimaryOwner().getName().equals(transferSendMoneyDTO.getNameReceiver())) {
                Money newBalanceSender = new Money(accountSender.getBalance().decreaseAmount(transferSendMoneyDTO.getAmountMoney()));
                accountSender.setBalance(newBalanceSender);
                Money newBalanceReceiver = new Money(accountReceiver.getBalance().increaseAmount(transferSendMoneyDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceReceiver);
                //System.out.println(accountReceiver.getBalance()+ "-------- posterior recibidor");
                transfer = new Transfer(accountSender, accountReceiver, transferSendMoneyDTO.getAmountMoney(),
                        nameSender, transferSendMoneyDTO.getNameReceiver());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Transfer receiver's name is invalid." +
                                "The name provided doesn't match the owners of the account number given.");
            }
        }
        accountRepository.saveAll(List.of(accountReceiver, accountReceiver));
        transferRepository.save(transfer);

    }

    public List<Transfer> findMyTransfersReceiverByAccountId(Long accountId, long id) {
        Account myAccount = findMyAccountByAccountId(accountId, id);
        return transferRepository.findByAccountReceiver(myAccount);
    }

    public List<Transfer> findMyTransfersSenderByAccountId(Long accountId, long id) {
        Account myAccount = findMyAccountByAccountId(accountId, id);
        return transferRepository.findByAccountSenderAccountId(id);
    }
}
