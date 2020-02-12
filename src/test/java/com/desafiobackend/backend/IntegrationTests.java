package com.desafiobackend.backend;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.ProductSubscription;
import com.desafiobackend.backend.model.Status;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.repository.ProductRepository;
import com.desafiobackend.backend.repository.ProductSubscriptionRepository;
import com.desafiobackend.backend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.After;
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

    @Autowired
    protected ProductSubscriptionRepository productSubscriptionRepository;

    protected User testUser;

    protected Product product;

    protected User convertMapToObject(final Map response) throws Exception {
        final byte[] responseValue = objectMapper().writeValueAsBytes(response);
        return objectMapper().readValue(responseValue, User.class);
    }

    @Bean
    protected ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    protected void assertProductSubscription(final ProductSubscription productSubscription, final Status active) {
        Assertions.assertThat(productSubscription.getUserId()).isEqualTo(testUser.getId());
        Assertions.assertThat(productSubscription.getProductName()).isEqualTo(product.getName());
        Assertions.assertThat(productSubscription.getStatus()).isEqualTo(active);
    }

    protected void assertUserFieldByField(final User expectedUser, final User resultUser) {
        Assertions.assertThat(expectedUser.getName()).isEqualTo(resultUser.getName());
        Assertions.assertThat(expectedUser.getAddress()).isEqualTo(resultUser.getAddress());
        Assertions.assertThat(expectedUser.getEmail()).isEqualTo(resultUser.getEmail());
        Assertions.assertThat(expectedUser.getContactNumber()).isEqualTo(resultUser.getContactNumber());
        Assertions.assertThat(expectedUser.getCpf()).isEqualTo(resultUser.getCpf());
    }

    @After
    public void clean() {
        userRepository.deleteAll();
        productSubscriptionRepository.deleteAll();
        productRepository.deleteAll();
    }
}
