package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyToThirdPartyFromAHDTO;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyToAccountHolderFromAHDTO;
import com.ironhack.midtermproject.enums.StatusAccountEnum;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.transfers.TransferOwn;
import com.ironhack.midtermproject.models.transfers.TransferThirdPartyReceive;
import com.ironhack.midtermproject.models.users.AccountHolder;
import com.ironhack.midtermproject.models.users.ThirdParty;
import com.ironhack.midtermproject.repository.transfers.TransferThirdPartyReceiveRepository;
import com.ironhack.midtermproject.repository.transfers.TransferThirdPartySendRepository;
import com.ironhack.midtermproject.repository.users.ThirdPartyRepository;
import com.ironhack.midtermproject.repository.transfers.TransferOwnRepository;
import com.ironhack.midtermproject.repository.users.AccountHolderRepository;
import com.ironhack.midtermproject.repository.transfers.TransferRepository;
import com.ironhack.midtermproject.repository.accounts.AccountRepository;
import com.ironhack.midtermproject.service.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private TransferOwnRepository transferOwnRepository;

    @Autowired
    private TransferThirdPartyReceiveRepository transferThirdPartyReceiveRepository;
    @Autowired
    private TransferThirdPartySendRepository transferThirdPartySendRepository;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
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


    public void sendMoneyAccountHolder(Long accountId, long id, TransferSendMoneyToAccountHolderFromAHDTO transferSendMoneyToAccountHolderFromAHDTO) {
        Account accountSender = findMyAccountByAccountId(accountId, id);
        String nameSender = accountHolderRepository.findById(id).get().getName();
        Account accountReceiver = accountRepository.findById(transferSendMoneyToAccountHolderFromAHDTO.getAccountReceiverId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Account Receiver not found. Invalid account ID"));

        if(accountSender.getStatusAccount().equals(StatusAccountEnum.FROZEN ) || accountReceiver.getStatusAccount().equals(StatusAccountEnum.FROZEN) ){
            System.out.println("Status's account sending: "+accountSender.getStatusAccount());
            System.out.println("Status's account receiving: "+accountReceiver.getStatusAccount());
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
                    "Transaction not allowed account blocked");

        }
        if(isFraudForManyRequestsSenderOwn(accountSender) || isFraudForManyRequestsSenderThird(accountSender)|| isFraudForManyRequestsReceiver(accountReceiver) ||
                isFraudForAbnormalAmounts(accountSender, transferSendMoneyToAccountHolderFromAHDTO.getAmountMoney())){
            throw new ResponseStatusException(HttpStatus.LOCKED,
                    "The account has been blocked due to detection of attempted fraud.");
        }
        TransferOwn transfer;
        if(accountReceiver.getSecondaryOwner()!= null){
            if(accountReceiver.getPrimaryOwner().getName().equals(transferSendMoneyToAccountHolderFromAHDTO.getNameReceiver())
                    || accountReceiver.getSecondaryOwner().getName().equals(transferSendMoneyToAccountHolderFromAHDTO.getNameReceiver())) {
                Money newBalanceSender = new Money(accountSender.getBalance().decreaseAmount(transferSendMoneyToAccountHolderFromAHDTO.getAmountMoney()));
                accountSender.setBalance(newBalanceSender);
                Money newBalanceReceiver = new Money(accountReceiver.getBalance().increaseAmount(transferSendMoneyToAccountHolderFromAHDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceReceiver);
                transfer = new TransferOwn(accountReceiver, transferSendMoneyToAccountHolderFromAHDTO.getNameReceiver(), transferSendMoneyToAccountHolderFromAHDTO.getAmountMoney(),
                        accountSender, nameSender, LocalDateTime.now());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Transfer receiver's name is invalid." +
                                "The name provided doesn't match the owners of the account number given.");
            }
        }
        else{
            if(accountReceiver.getPrimaryOwner().getName().equals(transferSendMoneyToAccountHolderFromAHDTO.getNameReceiver())) {
                Money newBalanceSender = new Money(accountSender.getBalance().decreaseAmount(transferSendMoneyToAccountHolderFromAHDTO.getAmountMoney()));
                accountSender.setBalance(newBalanceSender);
                Money newBalanceReceiver = new Money(accountReceiver.getBalance().increaseAmount(transferSendMoneyToAccountHolderFromAHDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceReceiver);
                transfer = new TransferOwn(accountReceiver, transferSendMoneyToAccountHolderFromAHDTO.getNameReceiver(), transferSendMoneyToAccountHolderFromAHDTO.getAmountMoney(),
                        accountSender, nameSender, LocalDateTime.now());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Transfer receiver's name is invalid." +
                                "The name provided doesn't match the owners of the account number given.");
            }
        }
        accountRepository.saveAll(List.of(accountReceiver, accountReceiver));
        transferRepository.save(transfer);
        transferOwnRepository.save(transfer);

    }

    public void sendMoneyThirdParty(Long accountId, long id, TransferSendMoneyToThirdPartyFromAHDTO transferSendMoneyToThirdPartyFromAHDTO) {
        Account accountSender = findMyAccountByAccountId(accountId, id);
        String nameSender = accountHolderRepository.findById(id).get().getName();
        ThirdParty thirdParty = thirdPartyRepository.findById(transferSendMoneyToThirdPartyFromAHDTO.getIdThirdParty())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "ThirdParty not found. Invalid thirdParty ID"));

        if(accountSender.getStatusAccount().equals(StatusAccountEnum.FROZEN )){
            System.out.println("Status's account sending: "+accountSender.getStatusAccount());
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Transaction not allowed account blocked");
        }
        if(isFraudForManyRequestsSenderOwn(accountSender) || isFraudForManyRequestsSenderThird(accountSender) || isFraudForAbnormalAmounts(accountSender, transferSendMoneyToThirdPartyFromAHDTO.getAmountMoney())){
            throw new ResponseStatusException(HttpStatus.LOCKED,
                    "The account has been blocked due to detection of attempted fraud.");
        }
        TransferThirdPartyReceive transfer;
        // System.out.println(thirdParty.getHashKey() + " ---- en DB");
        // System.out.println(transferReceiveMoneyThirdPartyDTO.getHashKey() + " ---- en DTO");
        if(thirdParty.getHashKey().equals(transferSendMoneyToThirdPartyFromAHDTO.getHashKey())){
            Money newBalanceSender = new Money(accountSender.getBalance().decreaseAmount(transferSendMoneyToThirdPartyFromAHDTO.getAmountMoney()));
            accountSender.setBalance(newBalanceSender);
            transfer = new TransferThirdPartyReceive(accountSender, nameSender,
                    transferSendMoneyToThirdPartyFromAHDTO.getAmountMoney(), transferSendMoneyToThirdPartyFromAHDTO.getHashKey(), LocalDateTime.now());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "HashKey's receiver is invalid." +
                            "The hashKey provided doesn't match the owner of the thirdParty id given.");
        }
        accountRepository.save(accountSender);
        transferRepository.save(transfer);
        transferThirdPartyReceiveRepository.save(transfer);
    }

    public List<Transfer> findMyTransfersReceiverByAccountId(Long accountId, long id) {
        Account myAccount = findMyAccountByAccountId(accountId, id);
        List<Transfer> transfersOwn = transferOwnRepository.findByAccountReceiverOwn(myAccount);
        List<Transfer> transfersThird = transferThirdPartySendRepository.findByAccountReceiverThird(myAccount);
        List<Transfer> allTransferReceiver = Stream.concat(transfersOwn.stream(), transfersThird.stream()).collect(Collectors.toList());
        return allTransferReceiver;
    }

    public List<Transfer> findMyTransfersSenderByAccountId(Long accountId, long id) {
        Account myAccount = findMyAccountByAccountId(accountId, id);
        List<Transfer> transfersOwn = transferOwnRepository.findByAccountSenderOwn(myAccount);
        List<Transfer> transfersThird = transferThirdPartyReceiveRepository.findByAccountSenderThird(myAccount);
        List<Transfer> allTransferSender = Stream.concat(transfersOwn.stream(), transfersThird.stream()).collect(Collectors.toList());
        return allTransferSender;
    }

    public boolean isFraudForManyRequestsSenderOwn(Account account){
        List<Transfer> transferList = transferOwnRepository.findByAccountSenderOwnOrderByTimeTransferDesc(account);
        if(transferList.size() == 0){ return false; }
        long seconds = ChronoUnit.SECONDS.between(transferList.get(0).getTimeTransfer(), LocalDateTime.now());
        if(seconds <= 1.0) {
            account.setStatusAccount(StatusAccountEnum.FROZEN);
            accountRepository.save(account);
            System.out.println("The account "+account.getAccountId()+" has been cogelated for attempted fraud. " +
                    "Reason: too many transfers sent in less than a second.");
            return true;
        }
        return false;
    }
    public boolean isFraudForManyRequestsSenderThird(Account account){
        List<Transfer> transferList = transferThirdPartyReceiveRepository.findByAccountSenderThirdOrderByTimeTransferDesc(account);
        if(transferList.size() == 0){ return false; }
        long seconds = ChronoUnit.SECONDS.between(transferList.get(0).getTimeTransfer(), LocalDateTime.now());
        // System.out.println("seconds: "+seconds);
        if(seconds <= 1.0) {
            account.setStatusAccount(StatusAccountEnum.FROZEN);
            accountRepository.save(account);
            System.out.println("The account "+account.getAccountId()+" has been cogelated for attempted fraud. " +
                    "Reason: too many transfers sent in less than a second.");
            return true;
        }
        return false;
    }
    public boolean isFraudForManyRequestsReceiver(Account account){
        List<Transfer> transferList = transferOwnRepository.findByAccountReceiverOwnOrderByTimeTransferDesc(account);
        if(transferList.size() == 0){ return false; }
        long seconds = ChronoUnit.SECONDS.between(transferList.get(0).getTimeTransfer(), LocalDateTime.now());
        if(seconds <= 1.0) {
            account.setStatusAccount(StatusAccountEnum.FROZEN);
            System.out.println("The account "+account.getAccountId()+" has been cogelated for attempted fraud. " +
                    "Reason: too many transfers received in less than a second.");
            accountRepository.save(account);
            return true;
        }
        return false;
        //return (seconds <= 1.0) ? true: false; // operador ternario, forma cool if-else
    }
    public boolean isFraudForAbnormalAmounts(Account account, Money amount){
        return false;
    }
}
