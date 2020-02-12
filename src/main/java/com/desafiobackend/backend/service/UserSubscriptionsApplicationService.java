package com.desafiobackend.backend.service;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.ProductSubscription;
import com.desafiobackend.backend.repository.ProductRepository;
import com.desafiobackend.backend.repository.ProductSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSubscriptionsApplicationService {

    private final ProductSubscriptionRepository productSubscriptionRepository;

    private final ProductRepository productRepository;

    public List<Product> findUserProductSubscriptions(final String userId) {
        final List<ProductSubscription> productSubscriptions = Collections.unmodifiableList(productSubscriptionRepository.findUserSubscriptions(userId));
        final List<String> products = productSubscriptions.stream()
                .map(ProductSubscription::getProductName)
                .collect(Collectors.toList());
        
        return productRepository.findProductsInRange(products);
    }
}
