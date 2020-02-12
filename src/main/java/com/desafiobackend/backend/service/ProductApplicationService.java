package com.desafiobackend.backend.service;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductRepository productRepository;

    public Optional<Product> findProductByName(final String productName) {
        return productRepository.findProductByName(productName);
    }

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsInRangeId(List<String> products) {
        return Collections.unmodifiableList(productRepository.findProductsInRange(products));
    }
}
