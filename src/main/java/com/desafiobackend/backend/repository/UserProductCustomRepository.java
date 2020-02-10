package com.desafiobackend.backend.repository;

import com.desafiobackend.backend.model.UserProducts;

import java.util.Optional;

public interface UserProductCustomRepository {

    Optional<UserProducts> findActiveProductUserRelation(final String userId, final String productName);

}
