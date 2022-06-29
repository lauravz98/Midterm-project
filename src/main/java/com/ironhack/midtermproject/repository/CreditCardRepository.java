package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
