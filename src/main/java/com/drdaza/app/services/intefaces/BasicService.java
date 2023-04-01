package com.drdaza.app.services.intefaces;

public interface BasicService<T> {

    public T getOne(Long id);

    public T Save(T entity);
}
