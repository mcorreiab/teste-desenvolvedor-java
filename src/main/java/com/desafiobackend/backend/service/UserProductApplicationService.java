package com.desafiobackend.backend.service;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.Status;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.model.UserProducts;
import com.desafiobackend.backend.repository.UserProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductApplicationService {

    private final UserProductRepository userProductRepository;

    public void update(final UserProducts userProduct) {
        userProductRepository.save(userProduct);
    }

    public void save(UserProducts userProduct) {
        userProductRepository.insert(userProduct);
    }

    public void createProductInscription(final String userId, final String productName) {
        val newUserProduct = this.buildUserProduct(userId, productName);
        userProductRepository.insert(newUserProduct);
    }

    public List<Product> findAllProductsUser(final String userId) {
        return userProductRepository.findUserProducts(userId);
    }

    public List<User> findUsersOfProduct(final String productName) {
        return userProductRepository.findUsersOfProduct(productName);
    }

    private UserProducts buildUserProduct(final String userId, final String productName) {
        return UserProducts.builder()
                .idUser(userId)
                .productName(productName)
                .status(Status.ACTIVE)
                .inscriptionDateTime(LocalDateTime.now())
                .build();
    }
    
}
