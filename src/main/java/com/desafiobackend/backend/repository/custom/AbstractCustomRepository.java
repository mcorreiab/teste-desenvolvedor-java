package com.desafiobackend.backend.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class   AbstractCustomRepository {

    @Autowired
    protected MongoTemplate mongoTemplate;
}
