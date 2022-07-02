package com.ironhack.midtermproject.repository.transfers;

import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
