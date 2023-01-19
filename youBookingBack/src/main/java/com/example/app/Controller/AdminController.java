package com.example.app.Controller;


import com.example.app.Entity.appRole;
import com.example.app.Entity.appUser;
import com.example.app.Security.JWTUtil;
import com.example.app.Sevice.Impl.AdminServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import static com.example.app.Security.JWTUtil.SECRET;

@RestController
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;


    @GetMapping(path = "/users")
    @PostAuthorize("hasAnyAuthority('Admin')")
    public List<appUser> userList(){
        return adminService.listUser();
    }

    @PostMapping(path = "/users")
    @PostAuthorize("hasAnyAuthority('Admin')")
    appUser saveUser(@RequestBody appUser user){
        return adminService.saveUser(user);
    }

    @PostMapping(path = "/register")
    appUser RegisterClient(@RequestBody appUser user){
        return adminService.Register(user);
    }

    @PostMapping(path = "/roles")
    @PostAuthorize("hasAnyAuthority('Admin')")
    appRole saveRole(@RequestBody appRole role){
        return adminService.addNewRole(role);
    }

    @PostMapping(path = "/addRoleToUser")
    @PostAuthorize("hasAnyAuthority('Admin')")
    public String addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        adminService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
        return "{message succes}";
    }

    @GetMapping(path = "/utlisateur")
    public String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}


@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
