package com.ironhack.midtermproject.repository.transfers;

import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.transfers.TransferThirdPartyReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferThirdPartyReceiveRepository extends JpaRepository<TransferThirdPartyReceive, Long> {
    List<Transfer> findByAccountSenderThird(Account myAccount);

    List<Transfer> findByHashKey(String hashKey);
    //List<Transfer> findByAccountReceiver(Account accountReceiver);
}
