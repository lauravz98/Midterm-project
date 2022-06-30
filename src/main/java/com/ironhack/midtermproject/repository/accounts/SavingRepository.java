package com.ironhack.midtermproject.repository.accounts;

import com.ironhack.midtermproject.models.accounts.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
}
