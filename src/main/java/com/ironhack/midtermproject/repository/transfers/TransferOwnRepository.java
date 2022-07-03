package com.ironhack.midtermproject.repository.transfers;

import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.transfers.TransferOwn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferOwnRepository extends JpaRepository<TransferOwn, Long> {
    List<Transfer> findByAccountSenderOwn(Account myAccount);
    //List<Transfer> findByAccountSenderAccountId(Long accountSenderId);
    List<Transfer> findByAccountReceiverOwn(Account accountReceiver);


    List<Transfer> findByAccountSenderOwnOrderByTimeTransferDesc(Account myAccount);
    List<Transfer> findByAccountReceiverOwnOrderByTimeTransferDesc(Account myAccount);
}
