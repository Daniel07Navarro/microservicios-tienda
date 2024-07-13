package com.daniel.products_service.controllers;

import com.daniel.products_service.DTO.ProductDTO;
import com.daniel.products_service.model.Product;
import com.daniel.products_service.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() throws Exception{
        var list= productService.getAll()
                .stream()
                .map(p -> modelMapper.map(p,ProductDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody ProductDTO productDTO) throws Exception{
        Product product = productService.save(modelMapper.map(productDTO, Product.class));
        return new ResponseEntity<>(modelMapper.map(product,ProductDTO.class), HttpStatus.CREATED);
    }





}
