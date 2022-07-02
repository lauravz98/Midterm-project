package com.ironhack.midtermproject.models.users;

import com.ironhack.midtermproject.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String username;

    private String password;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    public User() {
    }
    protected User(String name, String password) {
        this.name = name;
        this.password = password;
        this.userRoleEnum = null;
        this.username = createUsername(name, id);

    }
    public User(String name, String password, UserRoleEnum userRoleEnum) {
        this.name = name;
        this.password = password;
        this.userRoleEnum = userRoleEnum;
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

    public UserRoleEnum getUserRole() {
        return userRoleEnum;
    }

    public void setUserRole(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
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
