package com.desafiobackend.backend.service;

import com.desafiobackend.backend.model.ProductSubscription;
import com.desafiobackend.backend.model.Status;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.repository.ProductSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSubscriptionApplicationService {

    private final UserApplicationService userApplicationService;

    private final ProductSubscriptionRepository productSubscriptionRepository;

    public void update(final ProductSubscription userProduct) {
        productSubscriptionRepository.save(userProduct);
    }

    public void createProductInscription(final String userId, final String productName) {
        val newUserProduct = this.buildUserProduct(userId, productName);
        productSubscriptionRepository.insert(newUserProduct);
    }

    public Optional<ProductSubscription> findCurrentProductSubscription(final String userId, final String productName) {
        return productSubscriptionRepository.findActiveProductUserRelation(userId, productName);
    }

    public List<User> findUsersOfProduct(final String productName) {
        if (Objects.isNull(productName)) {
            return userApplicationService.findAll();
        }
        val users = productSubscriptionRepository.findSubscribersOfProduct(productName).stream()
                .map(ProductSubscription::getUserId)
                .collect(Collectors.toList());
        return userApplicationService.findAllById(users);
    }

    private ProductSubscription buildUserProduct(final String userId, final String productName) {
        return ProductSubscription.builder()
                .userId(userId)
                .productName(productName)
                .status(Status.ACTIVE)
                .inscriptionDateTime(LocalDateTime.now())
                .build();
    }

}
