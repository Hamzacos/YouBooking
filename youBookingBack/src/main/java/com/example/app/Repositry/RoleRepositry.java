package com.example.app.Repositry;

import com.example.app.Entity.appRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositry extends JpaRepository<appRole,Long> {

    appRole findByRoleName(String roleName);
}
