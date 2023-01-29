package com.sacavix.todoapp.service;

import java.util.List;

public interface ICRUD<T,ID> {
    List<T> findAll() throws Exception;
    T findById(ID id) throws Exception;
    T create(T t) throws Exception;
    T update(T t) throws Exception;
    void delete(ID id) throws Exception;
}
