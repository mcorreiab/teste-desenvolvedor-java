package com.desafiobackend.backend.repository;

import com.desafiobackend.backend.model.ProductSubscription;
import com.desafiobackend.backend.repository.custom.ProductSubscriptionCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSubscriptionRepository extends MongoRepository<ProductSubscription, String>, ProductSubscriptionCustomRepository {
}
