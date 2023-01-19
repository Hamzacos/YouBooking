package com.example.app.DTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class myUser extends User {


    private int id;
    private String fullName;


    public myUser (int id,String username, String password,Collection<? extends GrantedAuthority> authorities,
                   String fullName) {
        super(username, password, authorities);
        this.id = id;
        this.fullName = fullName;
        }


    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
