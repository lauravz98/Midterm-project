package com.ironhack.midtermproject.repository.transfers;

import com.ironhack.midtermproject.models.transfers.Transfer;
import com.ironhack.midtermproject.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query(value = "SELECT MAX(sum_per_day.total_day) FROM " +
            "(SELECT date_transfer, SUM(amount) AS total_day FROM transfer GROUP BY date_transfer) AS sum_per_day", nativeQuery = true)
    double findMaxDailyFromSumAmount();

}
