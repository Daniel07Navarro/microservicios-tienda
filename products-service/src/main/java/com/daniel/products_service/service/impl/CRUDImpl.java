package com.daniel.products_service.service.impl;



import com.daniel.products_service.repository.IGenericRepository;
import com.daniel.products_service.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {

    abstract IGenericRepository<T,ID> getRepo();

    @Override
    public List<T> getAll() throws Exception{
        return getRepo().findAll();
    }

    @Override
    public T getById(ID id) throws Exception{
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public T save(T t)  throws Exception{
        return getRepo().save(t);
    }

    @Override
    public T update(T t)  throws Exception{
        return getRepo().save(t);
    }

    @Override
    public void delete(T t)  throws Exception{
        getRepo().delete(t);
    }
}
