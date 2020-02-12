package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.Status;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductCustomRepositoryImpl extends AbstractCustomRepository implements ProductCustomRepository {

    @Override
    public Optional<Product> findProductByName(final String productName) {
        final Query query = createQuery(productName);
        return Optional.ofNullable(mongoTemplate.findOne(query, Product.class));
    }

    @Override
    public List<Product> findProductsInRange(List<String> subscriptions) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("name").in(subscriptions));
        return Collections.unmodifiableList(mongoTemplate.find(query, Product.class));
    }

    private Query createQuery(final String productName) {
        return new Query().addCriteria(createCriteria(productName));
    }

    private Criteria createCriteria(final String productName) {
        return Criteria.where("name").is(productName)
                .and("status").is(Status.ACTIVE);
    }
}
