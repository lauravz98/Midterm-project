package com.ironhack.midtermproject.service.impl;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyToAHFromThirdPartyDTO;
import com.ironhack.midtermproject.controller.dto.TransferThirdPartyGetMoneyFromAHDTO;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.transfers.TransferThirdPartyReceive;
import com.ironhack.midtermproject.models.transfers.TransferThirdPartySend;
import com.ironhack.midtermproject.models.users.ThirdParty;
import com.ironhack.midtermproject.repository.accounts.AccountRepository;
import com.ironhack.midtermproject.repository.transfers.TransferRepository;
import com.ironhack.midtermproject.repository.transfers.TransferThirdPartyReceiveRepository;
import com.ironhack.midtermproject.repository.transfers.TransferThirdPartySendRepository;
import com.ironhack.midtermproject.repository.users.ThirdPartyRepository;
import com.ironhack.midtermproject.service.interfaces.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static com.ironhack.midtermproject.utils.utils.getDateNow;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private TransferThirdPartyReceiveRepository transferThirdPartyReceiveRepository;
    @Autowired
    private TransferThirdPartySendRepository transferThirdPartySendRepository;
    private boolean validThirdParty(Long thirdId, String hashKey){
        ThirdParty thirdParty = thirdPartyRepository.findById(thirdId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "ThirdParty not found. Invalid thirdParty ID in the header's Request"));
        if(thirdParty.getHashKey().equals(hashKey)){ return true; }
        else { return false; }
    }
    public List<Transfer> findTransferReceiver(Long thirdId, String hashKey) {
        if(validThirdParty(thirdId, hashKey)){
            List<Transfer> transfersThird = transferThirdPartyReceiveRepository.findByHashKey(hashKey);
            return transfersThird;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "HashKey is invalid. The hashKey provided doesn't match with the id's thirdParty given.");
        }
    }

    public List<Transfer> findTransferSender(Long thirdId, String hashKey) {
        if(validThirdParty(thirdId, hashKey)){
            List<Transfer> transfersThird = transferThirdPartySendRepository.findByHashKey(hashKey);
            return transfersThird;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "HashKey is invalid. The hashKey provided doesn't match with the id's thirdParty given.");
        }
    }

    public void sendMoneyAccountHolder(Long thirdId, String hashKey, TransferSendMoneyToAHFromThirdPartyDTO transferSendMoneyToAHFromThirdPartyDTO) {
        Account accountReceiver;
        TransferThirdPartySend transfer;
        if(validThirdParty(thirdId, hashKey)){
            accountReceiver = accountRepository.findById(transferSendMoneyToAHFromThirdPartyDTO.getIdAccount())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Account Receiver not found. Invalid account ID"));
            if (accountReceiver.getSecretKey().equals(transferSendMoneyToAHFromThirdPartyDTO.getSecretKey())){
                Money newBalanceReceiver = new Money(accountReceiver.getBalance().increaseAmount(transferSendMoneyToAHFromThirdPartyDTO.getAmountMoney()));
                accountReceiver.setBalance(newBalanceReceiver);

                transfer = new TransferThirdPartySend(accountReceiver, transferSendMoneyToAHFromThirdPartyDTO.getAmountMoney(), hashKey,
                        transferSendMoneyToAHFromThirdPartyDTO.getNameReceiver(), transferSendMoneyToAHFromThirdPartyDTO.getSecretKey(), LocalDateTime.now());
            } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "SecretKey is invalid. The secret key provided doesn't match with the account given.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "HashKey is invalid. The hashKey provided doesn't match with the id's thirdParty given.");
        }
        accountRepository.save(accountReceiver);
        transferRepository.save(transfer);
        transferThirdPartySendRepository.save(transfer);
    }

    public void getMoneyFromAccountHolder(Long thirdId, String hashKey, TransferThirdPartyGetMoneyFromAHDTO transferThirdPartyGetMoneyFromAHDTO) {
        Account accountSender;
        TransferThirdPartyReceive transfer;
        if(validThirdParty(thirdId, hashKey)){
            accountSender = accountRepository.findById(transferThirdPartyGetMoneyFromAHDTO.getIdAccount())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Account sender not found. Invalid account ID"));
            if (accountSender.getSecretKey().equals(transferThirdPartyGetMoneyFromAHDTO.getSecretKey())){
                Money newBalanceReceiver = new Money(accountSender.getBalance().decreaseAmount(transferThirdPartyGetMoneyFromAHDTO.getAmountMoney()));
                accountSender.setBalance(newBalanceReceiver);

                transfer = new TransferThirdPartyReceive(accountSender, transferThirdPartyGetMoneyFromAHDTO.getNamePrimaryOwner(),
                        transferThirdPartyGetMoneyFromAHDTO.getAmountMoney(), hashKey, LocalDateTime.now());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "SecretKey is invalid. The secret key provided doesn't match with the account given.");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "HashKey is invalid. The hashKey provided doesn't match with the id's thirdParty given.");
        }
        accountRepository.save(accountSender);
        transferRepository.save(transfer);
        transferThirdPartyReceiveRepository.save(transfer);
    }
}
