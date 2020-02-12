package com.desafiobackend.backend.service;

import com.desafiobackend.backend.exception.ProductNotFoundException;
import com.desafiobackend.backend.exception.UserNotFoundException;
import com.desafiobackend.backend.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscribeProductApplicationService {

    private final UserApplicationService userApplicationService;

    private final ProductApplicationService productApplicationService;

    private final ProductSubscriptionApplicationService productSubscriptionApplicationService;

    public void relateProduct(final String productName, final String userId) {
        userApplicationService.findUserById(userId)
                .orElseThrow(UserNotFoundException::new);

        productApplicationService.findProductByName(productName)
                .orElseThrow(ProductNotFoundException::new);

        productSubscriptionApplicationService.findCurrentProductSubscription(userId, productName)
                .ifPresent(userProduct -> {
                    userProduct.setStatus(Status.INACTIVE);
                    userProduct.setCancelDateTime(LocalDateTime.now());
                    productSubscriptionApplicationService.update(userProduct);
                });

        productSubscriptionApplicationService.createProductInscription(userId, productName);
    }

}
