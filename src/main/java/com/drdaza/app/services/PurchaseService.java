package com.drdaza.app.services;

import org.springframework.stereotype.Service;

import com.drdaza.app.exceptions.PurchaseNotFounException;
import com.drdaza.app.models.Purchase;
import com.drdaza.app.repository.PurchaseRepository;
import com.drdaza.app.services.intefaces.BasicService;


@Service
public class PurchaseService implements BasicService<Purchase>{

    private PurchaseRepository purchaseRepository;

    

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase Save(Purchase entity) {
        return purchaseRepository.save(entity);
    }

    @Override
    public Purchase getOne(Long id) {
        return purchaseRepository.findById(id).orElseThrow(()-> new PurchaseNotFounException("Purchase not found"));
    }
    
}
