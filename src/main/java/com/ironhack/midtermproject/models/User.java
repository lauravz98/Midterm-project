package com.ironhack.midtermproject.models;

import com.ironhack.midtermproject.enums.UserRole;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String username;

    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.userRole = null;
        this.username = createUsername(name, id);

    }
    public User(String name, String password, UserRole userRole) {
        this.name = name;
        this.password = password;
        this.userRole = userRole;
        this.username = createUsername(name, id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String createUsername(String name, long id){
        String username = "";
        try {
            String[] splitName = name.split(" ");
            for(String word: splitName){
                username += word + ".";
            }
            username = username+id;
        } catch (ArrayIndexOutOfBoundsException e){
            username = username+id;
        }
        return username;
    }
}
