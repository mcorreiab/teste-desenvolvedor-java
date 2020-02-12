package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.ProductSubscription;

import java.util.List;
import java.util.Optional;

public interface ProductSubscriptionCustomRepository {

    Optional<ProductSubscription> findActiveProductUserRelation(final String userId, final String productName);

    List<ProductSubscription> findUserSubscriptions(final String userId);

    List<ProductSubscription> findSubscribersOfProduct(final String productName);

}
