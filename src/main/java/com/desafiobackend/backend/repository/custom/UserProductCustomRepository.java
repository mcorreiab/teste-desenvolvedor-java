package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.model.UserProducts;

import java.util.List;
import java.util.Optional;

public interface UserProductCustomRepository {

    Optional<UserProducts> findActiveProductUserRelation(final String userId, final String productName);

    Optional<Product> findProductByName(final String productName);

    List<Product> findUserProducts(final String userId);

    List<User> findUsersOfProduct(final String userId);

}
