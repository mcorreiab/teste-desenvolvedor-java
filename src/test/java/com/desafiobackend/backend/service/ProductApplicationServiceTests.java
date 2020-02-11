package com.desafiobackend.backend.service;

import com.desafiobackend.backend.IntegrationTests;
import com.desafiobackend.backend.ProductSubscriptionEndpoint;
import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.scenario.UserTestScenarioSchema;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductApplicationServiceTests extends IntegrationTests {

    @Autowired
    private ProductApplicationService productApplicationService;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
        testUser = userRepository.insert(UserTestScenarioSchema.createSpockUser());
        product = productRepository.insert(createNewProduct());
    }

    @Test
    public void givenAUser_AndAProduct_WhenRequestProductInscription_ShouldGenerateNewUserSubscription() {
        ProductSubscriptionEndpoint.relateProduct(testUser.getId(), product.getId());
    }

    @Test
    public void givenUserWithExistingActiveProductInscription_WhenRequestNewProductInscription_ShouldInactivateAndCreateNewSubscription() {
        ProductSubscriptionEndpoint.relateProduct(testUser.getId(), product.getId());
    }

    @Test
    public void givenExistingUser_AndNotExistingProduct_ThenReturnProductNotFoundException() {
        ProductSubscriptionEndpoint.relateProduct(testUser.getId(), product.getId());
    }

    @Test
    public void givenNotExistingUser_AndExistingProduct_ThenReturnUserNotFoundException() {
        ProductSubscriptionEndpoint.relateProduct(testUser.getId(), product.getId());
    }

    @Test
    public void givenUserWithProductInscriptions_WhenRequestProductsSubscriptions_ShouldListAll() {
        ProductSubscriptionEndpoint.listProductsByUser(testUser.getId());
    }

    @Test
    public void givenUserWithProductInscriptions_WhenRequestUserProductSubscriptions_ShouldListAll() {
        ProductSubscriptionEndpoint.findUsersByProduct(product.getId());
    }

    public Product createNewProduct() {
        return Product.builder()
                .name("COMUM")
                .dateTime(LocalDateTime.now())
                .build();
    }
}
