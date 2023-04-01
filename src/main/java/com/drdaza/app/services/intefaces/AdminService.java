package com.drdaza.app.services.intefaces;

import java.util.List;

public interface AdminService<T> {
    
    public List<T> listAll();
    public void delete(T entity);
    public void deleteById(Long id);
    public void update(Long id, T entity);
    
}
