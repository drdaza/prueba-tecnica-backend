package com.drdaza.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drdaza.app.models.PayMethod;

public interface PayMethodRepository extends JpaRepository<PayMethod, Long>{
    
}
