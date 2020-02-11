package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.Status;
import com.desafiobackend.backend.model.UserProducts;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProductCustomRepositoryImpl extends AbstractCustomRepository implements UserProductCustomRepository {

    @Override
    public Optional<UserProducts> findActiveProductUserRelation(final String userId, final String productName) {
        final Query query = createQuery(userId, productName);
        final UserProducts queryResult = mongoTemplate.findOne(query, UserProducts.class);
        return Optional.ofNullable(queryResult);
    }

    @Override
    public Optional<Product> findProductByName(String productName) {
        final Query query = new Query();
        query.addCriteria(Criteria
                .where("productName").is(productName)
                .and("status").is(Status.ACTIVE));

        return Optional.ofNullable(mongoTemplate.findOne(query, Product.class));
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
