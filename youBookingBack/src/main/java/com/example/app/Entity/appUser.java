package com.example.app.Entity;

import com.example.app.Entity.appRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class appUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
        private String fullName;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<appRole> userRoles = new ArrayList<>();

    public void addRole(appRole role){
        this.getUserRoles().add(role);
    }
}
