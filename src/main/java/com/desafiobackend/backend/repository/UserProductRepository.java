package com.desafiobackend.backend.repository;

import com.desafiobackend.backend.model.UserProducts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepository extends MongoRepository<UserProducts, String> {
}
