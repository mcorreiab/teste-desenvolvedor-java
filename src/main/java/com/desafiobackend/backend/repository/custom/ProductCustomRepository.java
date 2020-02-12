package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductCustomRepository {

    Optional<Product> findProductByName(final String productName);

    List<Product> findProductsInRange(List<String> subscriptions);
}
