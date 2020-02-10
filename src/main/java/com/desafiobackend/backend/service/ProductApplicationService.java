package com.desafiobackend.backend.service;

import com.desafiobackend.backend.exception.ProductNotFoundException;
import com.desafiobackend.backend.exception.UserNotFoundException;
import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.Status;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.model.UserProducts;
import com.desafiobackend.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final UserApplicationService userApplicationService;

    private final ProductRepository productRepository;

    private final UserProductApplicationService userProductApplicationService;

    public void relateProduct(final Product product, final String userId) {
        final User user = userApplicationService.findUserById(userId)
                .orElseThrow(UserNotFoundException::new);
        productRepository.findById(product.getId())
                .orElseThrow(ProductNotFoundException::new);

        productRepository.findActiveProductUserRelation(user.getId(), product.getName())
                .ifPresent(userProduct -> {
                    userProduct.setStatus(Status.INACTIVE);
                    userProduct.setCancelDateTime(LocalDateTime.now());
                    userProductApplicationService.update(userProduct);
                });

        val userProduct = UserProducts.builder()
                .idUser(userId)
                .productName(product.getName())
                .status(Status.ACTIVE)
                .inscriptionDateTime(LocalDateTime.now())
                .build();

        userProductApplicationService.save(userProduct);
    }

}
