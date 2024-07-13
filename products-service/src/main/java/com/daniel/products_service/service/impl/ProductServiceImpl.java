package com.daniel.products_service.service.impl;


import com.daniel.products_service.model.Product;
import com.daniel.products_service.repository.IGenericRepository;
import com.daniel.products_service.repository.IProductRepository;
import com.daniel.products_service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CRUDImpl<Product,Integer> implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    IGenericRepository<Product, Integer> getRepo() {
        return productRepository;
    }
}
