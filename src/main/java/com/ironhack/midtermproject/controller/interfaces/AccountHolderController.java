package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyDTO;
import com.ironhack.midtermproject.models.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.security.CustomUserDetails;

import java.util.List;
import java.util.Set;

public interface AccountHolderController {

    Set<Account> findMyAccountsByAccountHolderId(CustomUserDetails userDetails);
    Account findMyAccountByAccountId(Long accountId, CustomUserDetails userDetails);
    Money findMyBalanceByAccountId(Long accountId, CustomUserDetails userDetails);
    List<Transfer> findMyTransfersReceiverByAccountId(Long accountId, CustomUserDetails userDetails);

    List<Transfer> findMyTransfersSenderByAccountId(Long accountId, CustomUserDetails userDetails);
    void sendMoney(Long accountId, CustomUserDetails userDetails, TransferSendMoneyDTO transferSendMoneyDTO);
}
