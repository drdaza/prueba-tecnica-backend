package com.drdaza.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drdaza.app.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    
}
