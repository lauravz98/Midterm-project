package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.controller.dto.TransferReceiveMoneyThirdPartyDTO;
import com.ironhack.midtermproject.controller.dto.TransferSendMoneyAccountHolderDTO;
import com.ironhack.midtermproject.models.transfers.Transfer;
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
    void sendMoneyAccountHolder(Long accountId, CustomUserDetails userDetails, TransferSendMoneyAccountHolderDTO transferSendMoneyAccountHolderDTO);
    void sendMoneyThirdParty(Long accountId, CustomUserDetails userDetails, TransferReceiveMoneyThirdPartyDTO transferReceiveMoneyThirdPartyDTO);
}
