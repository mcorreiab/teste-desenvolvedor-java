package com.desafiobackend.backend;

import com.desafiobackend.backend.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

import java.util.Map;

public class IntegrationTests {

    protected User convertMapToObject(Map response) throws Exception {
        final byte[] responseValue = objectMapper().writeValueAsBytes(response);
        return objectMapper().readValue(responseValue, User.class);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
