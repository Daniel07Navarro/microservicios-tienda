package com.daniel.products_service.service;

import java.util.List;

public interface ICRUD<T,ID> {

    List<T> getAll() throws Exception;

    T getById(ID id) throws Exception;

    T save(T t) throws Exception;

    T update(T t) throws Exception;

    void delete(T t) throws Exception;

}
