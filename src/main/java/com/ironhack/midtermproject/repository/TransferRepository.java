package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.models.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByAccountSenderAccountId(Long accountSenderId);
    List<Transfer> findByAccountReceiver(Account accountReceiver);
}
