package com.drdaza.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drdaza.app.models.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    
}
