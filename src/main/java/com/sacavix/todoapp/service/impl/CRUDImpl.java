package com.sacavix.todoapp.service.impl;

import com.sacavix.todoapp.repository.IGenericRepository;
import com.sacavix.todoapp.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {
    public abstract IGenericRepository<T,ID> getRepository();

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public T create(T t) {
        return getRepository().save(t);
    }

    @Override
    public T update(T t) {return getRepository().save(t);}

    @Override
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
}
