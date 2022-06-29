package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
