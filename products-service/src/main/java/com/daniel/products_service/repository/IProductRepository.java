package com.daniel.products_service.repository;


import com.daniel.products_service.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends IGenericRepository<Product, Integer> {
}
