package com.desafiobackend.backend.service;

import com.desafiobackend.backend.exception.ProductNotFoundException;
import com.desafiobackend.backend.exception.UserNotFoundException;
import com.desafiobackend.backend.model.Status;
import com.desafiobackend.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final UserApplicationService userApplicationService;

    private final ProductRepository productRepository;

    private final UserProductApplicationService userProductApplicationService;

    public void relateProduct(final String productName, final String userId) {
        userApplicationService.findUserById(userId)
                .orElseThrow(UserNotFoundException::new);

        productRepository.findProductByName(productName)
                .orElseThrow(ProductNotFoundException::new);

        productRepository.findActiveProductUserRelation(userId, productName)
                .ifPresent(userProduct -> {
                    userProduct.setStatus(Status.INACTIVE);
                    userProduct.setCancelDateTime(LocalDateTime.now());
                    userProductApplicationService.update(userProduct);
                });

        userProductApplicationService.createProductInscription(userId, productName);
    }

}
