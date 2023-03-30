package com.drdaza.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drdaza.app.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
