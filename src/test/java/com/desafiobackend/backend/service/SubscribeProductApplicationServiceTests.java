package com.desafiobackend.backend.service;

import com.desafiobackend.backend.IntegrationTests;
import com.desafiobackend.backend.ProductSubscriptionEndpoint;
import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.Status;
import com.desafiobackend.backend.scenario.UserTestScenarioSchema;
import io.restassured.RestAssured;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscribeProductApplicationServiceTests extends IntegrationTests {

    @Autowired
    private SubscribeProductApplicationService subscribeProductApplicationService;

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
        ProductSubscriptionEndpoint.relateProduct(product.getName(), testUser.getId())
                .statusCode(HttpStatus.OK.value());

        val productSubscription = productSubscriptionRepository.findAll().get(0);
        assertProductSubscription(productSubscription, Status.ACTIVE);
    }

    @Test
    public void givenUserWithExistingActiveProductInscription_WhenRequestNewProductInscription_ShouldInactivateAndCreateNewSubscription() {
        //first call
        ProductSubscriptionEndpoint.relateProduct(product.getName(), testUser.getId())
                .statusCode(HttpStatus.OK.value());
        //second call
        ProductSubscriptionEndpoint.relateProduct(product.getName(), testUser.getId())
                .statusCode(HttpStatus.OK.value());

        val firstSubscription = productSubscriptionRepository.findAll().get(0);
        assertProductSubscription(firstSubscription, Status.INACTIVE);

        val secondSubscription = productSubscriptionRepository.findAll().get(1);
        assertProductSubscription(secondSubscription, Status.ACTIVE);
    }

    @Test
    public void givenExistingUser_AndNotExistingProduct_ThenReturnProductNotFoundException() {
        productRepository.deleteAll();
        ProductSubscriptionEndpoint.relateProduct(product.getName(), testUser.getId())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Product Not Found"));
    }

    @Test
    public void givenNotExistingUser_AndExistingProduct_ThenReturnUserNotFoundException() {
        userRepository.deleteAll();
        ProductSubscriptionEndpoint.relateProduct(product.getName(), testUser.getId())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("User Not Found"));
    }

    @Test
    public void givenUserWithProductInscriptions_WhenRequestProductsSubscriptions_ShouldListUsers() throws Exception {
        ProductSubscriptionEndpoint.relateProduct(product.getName(), testUser.getId())
                .statusCode(HttpStatus.OK.value());

        ProductSubscriptionEndpoint.listUserProductSubscriptions(testUser.getId())
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id[0]", equalTo(product.getId()))
                .body("name[0]", equalTo(product.getName()))
                .body("dateTime[0]", equalTo(product.getDateTime().toString()))
                .body("status[0]", equalTo(product.getStatus().name()));
    }

    @Test
    public void givenUserWithProductInscriptions_WhenRequestProductsSubscriptionsWithoutParams_ShouldListAll() throws Exception {
        final List<Map> userResponse = ProductSubscriptionEndpoint.listAllUsers()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList("$");

        val user = userRepository.findById(testUser.getId()).orElse(null);
        Assertions.assertThat(user).isEqualToComparingFieldByField(convertMapToObject(userResponse.get(0)));
    }

    @Test
    public void givenUserWithProductInscriptions_WhenRequestUserProductSubscriptions_ShouldListAll() throws Exception {
        ProductSubscriptionEndpoint.relateProduct(product.getName(), testUser.getId())
                .statusCode(HttpStatus.OK.value());

        final List<Map> result = ProductSubscriptionEndpoint.listProductSubscribers(product.getName())
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList("$");

        assertUserFieldByField(testUser, convertMapToObject(result.get(0)));
    }

    public Product createNewProduct() {
        return Product.builder()
                .name("COMUM")
                .dateTime(LocalDateTime.now())
                .status(Status.ACTIVE)
                .build();
    }
}
