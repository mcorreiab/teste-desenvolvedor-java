package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.UserProducts;

import java.util.Optional;

public interface UserProductCustomRepository {

    Optional<UserProducts> findActiveProductUserRelation(final String userId, final String productName);

    Optional<Product> findProductByName(final String productName);
}
