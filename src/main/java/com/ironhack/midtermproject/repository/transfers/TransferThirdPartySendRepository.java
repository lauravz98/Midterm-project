package com.ironhack.midtermproject.repository.transfers;

import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.transfers.TransferThirdPartyReceive;
import com.ironhack.midtermproject.models.transfers.TransferThirdPartySend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferThirdPartySendRepository extends JpaRepository<TransferThirdPartySend, Long> {
    List<Transfer> findByAccountReceiverThird(Account accountReceiver);
}
