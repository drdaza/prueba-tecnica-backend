package com.drdaza.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drdaza.app.models.User;

public interface UserRepository extends JpaRepository<User, String>{
    public Optional<User> findByUsername(String username);
}
