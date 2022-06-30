package com.ironhack.midtermproject.models.users;

import com.ironhack.midtermproject.enums.UserRole;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String name, String password) {
        super(name, password);
        setUserRole(UserRole.ADMIN);
    }


}
