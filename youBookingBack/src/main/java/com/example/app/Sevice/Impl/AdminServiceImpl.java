package com.example.app.Sevice.Impl;

import com.example.app.Entity.appRole;
import com.example.app.Entity.appUser;
import com.example.app.Repositry.RoleRepositry;
import com.example.app.Repositry.UserRepository;
import com.example.app.Sevice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepositry roleRepositry;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public appUser saveUser(appUser appUser) {
        String pw = appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return userRepository.save(appUser);
    }

    @Override
    public appUser Register(appUser appUser) {
        appRole roleClient = roleRepositry.findByRoleName("Client");
        appUser.addRole(roleClient);
        return userRepository.save(appUser);
    }
    @Override
    public appRole addNewRole(appRole role) {
        return roleRepositry.save(role);
    }

    @Override
     public void addRoleToUser(String username, String roleName) {
        appUser user = userRepository.findByUsername(username);
        appRole role = roleRepositry.findByRoleName(roleName);
        user.getUserRoles().add(role);
        userRepository.save(user);
    }


    @Override
    public List<appUser> listUser() {
        return userRepository.findAll();
    }

    @Override
    public appUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
