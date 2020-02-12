package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.ProductSubscription;
import com.desafiobackend.backend.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductSubscriptionCustomRepositoryImpl implements ProductSubscriptionCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<ProductSubscription> findActiveProductUserRelation(final String userId, final String productName) {
        final Query query = createQuery(userId, productName);
        final ProductSubscription queryResult = mongoTemplate.findOne(query, ProductSubscription.class);
        return Optional.ofNullable(queryResult);
    }

    @Override
    public List<ProductSubscription> findUserSubscriptions(String userId) {
        final Query query = new Query();
        query.addCriteria(Criteria
                .where("userId").is(userId));

        return Collections.unmodifiableList(mongoTemplate.find(query, ProductSubscription.class));
    }

    @Override
    public List<ProductSubscription> findSubscribersOfProduct(String productName) {
        final Query query = new Query();
        query.addCriteria(Criteria
                .where("productName").is(productName));

        return Collections.unmodifiableList(mongoTemplate.find(query, ProductSubscription.class));
    }

    private Query createQuery(final String userId, final String productName) {
        return new Query().addCriteria(createCriteria(userId, productName));
    }

    private Criteria createCriteria(final String userId, final String productName) {
        return Criteria.where("productName").is(productName)
                .and("userId").is(userId)
                .and("status").is(Status.ACTIVE);
    }

}
