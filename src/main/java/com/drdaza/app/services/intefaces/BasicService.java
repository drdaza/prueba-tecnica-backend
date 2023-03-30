package com.drdaza.app.services.intefaces;

import java.util.List;

public interface BasicService<T> {
    
    public List<T> listAll();
    public T getOne();
    public void Save(T entity);
}
