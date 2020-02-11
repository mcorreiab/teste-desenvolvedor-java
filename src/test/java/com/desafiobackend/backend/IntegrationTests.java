package com.desafiobackend.backend;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.repository.ProductRepository;
import com.desafiobackend.backend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;

@Profile("test")
@DirtiesContext
public class IntegrationTests {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ProductRepository productRepository;

    protected User testUser;

    protected Product product;

    protected User convertMapToObject(Map response) throws Exception {
        final byte[] responseValue = objectMapper().writeValueAsBytes(response);
        return objectMapper().readValue(responseValue, User.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
