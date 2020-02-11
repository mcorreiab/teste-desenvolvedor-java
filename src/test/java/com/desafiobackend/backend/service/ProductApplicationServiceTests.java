package com.desafiobackend.backend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductApplicationServiceTests {

    @Autowired
    private ProductApplicationService productApplicationService;

    @Test
    public void givenAUser_WhenRequestProductInscription_ShouldGenerateNewUserProduct() {
//        ProductEndpoint.relateProduct();
    }

    @Test
    public void givenUserWithExistingActiveProductInscription_WhenRequestNewProductInscription_ShouldInactivateAndCreateNewRelation() {
//        ProductEndpoint.relateProduct();
    }

    @Test
    public void givenNotExistingProduct_ThenReturnProductNotFoundException() {
//        ProductEndpoint.relateProduct();
    }

    @Test
    public void givenUserWithProductInscriptions_WhenRequestProductsSubscriptions_ShouldListAll() {
//        ProductEndpoint.listProductsByUser()
    }

    @Test
    public void givenUserWithProductInscriptions_WhenRequestUserProductSubscriptions_ShouldListAll() {
//        ProductEndpoint.findUsersByProduct();
    }

}
