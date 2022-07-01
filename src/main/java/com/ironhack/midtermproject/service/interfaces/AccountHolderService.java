package com.ironhack.midtermproject.service.interfaces;

import com.ironhack.midtermproject.controller.dto.TransferSendMoneyDTO;
import com.ironhack.midtermproject.models.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;

import java.util.List;
import java.util.Set;

public interface AccountHolderService {
    Set<Account> findMyAccountsByAccountHolderId(Long id);

    Account findMyAccountByAccountId(Long accountId, long id);

    void sendMoney(Long accountId, long id, TransferSendMoneyDTO transferSendMoneyDTO);

    List<Transfer> findMyTransfersReceiverByAccountId(Long accountId, long id);
    List<Transfer> findMyTransfersSenderByAccountId(Long accountId, long id);
}
