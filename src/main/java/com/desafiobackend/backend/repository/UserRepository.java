package com.desafiobackend.backend.repository;

import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.repository.custom.UserCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserCustomRepository {
}
