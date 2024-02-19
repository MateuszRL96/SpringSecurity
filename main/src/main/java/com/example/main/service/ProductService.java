package com.example.main.service;


import com.example.main.entity.ProductDTO;
import com.example.main.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDTO getProductDTO(){
        return null;
    }
}
