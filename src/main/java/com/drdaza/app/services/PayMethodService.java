package com.drdaza.app.services;

import com.drdaza.app.exceptions.PayMethodNotFound;
import com.drdaza.app.models.PayMethod;
import com.drdaza.app.repository.PayMethodRepository;
import com.drdaza.app.services.intefaces.BasicService;

public class PayMethodService implements BasicService<PayMethod> {

    private PayMethodRepository payMethodRepository;

    public PayMethodService(PayMethodRepository payMethodRepository) {
        this.payMethodRepository = payMethodRepository;
    }

    @Override
    public PayMethod getOne(Long id) {
        return payMethodRepository.findById(id).orElseThrow(()-> new PayMethodNotFound("the method doesn't exist"));
    }

    @Override
    public PayMethod Save(PayMethod entity) {
        return payMethodRepository.save(entity);
    }

}
