package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.models.accounts.Account;
import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.transfers.TransferOwn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferOwnRepository extends JpaRepository<TransferOwn, Long> {
    List<Transfer> findByAccountSender(Account myAccount);
}
