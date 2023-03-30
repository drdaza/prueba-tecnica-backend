package com.drdaza.app.services.intefaces;

public interface AdminService<T> {
    
    public void delete(T entity);
    public void delete(Long id);
    public void update(Long id, T entity);
    
}
