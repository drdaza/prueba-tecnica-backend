package com.drdaza.app.services.intefaces;

import java.util.List;

public interface AdminService<T> {
    
    public List<T> listAll();
    public void delete(Long id);
    public T update(Long id, T entity);
    
}
