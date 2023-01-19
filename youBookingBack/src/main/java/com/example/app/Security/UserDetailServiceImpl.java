package com.example.app.Security;

import com.example.app.DTO.myUser;
import com.example.app.Entity.appUser;
import com.example.app.Sevice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        appUser user = adminService.loadUserByUsername(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getUserRoles().forEach(r ->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new myUser(user.getId(),user.getUsername(),user.getPassword(),authorities,user.getFullName());
    }
}
