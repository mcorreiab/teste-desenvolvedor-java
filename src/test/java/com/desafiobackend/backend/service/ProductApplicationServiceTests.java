package com.desafiobackend.backend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductApplicationServiceTests {

    @Test
    public void givenAUser_WhenRequestProductInscription_ShouldGenerateNewUserProduct() {
    }

    @Test
    public void givenUserWithExistingActiveProductInscription_WhenRequestNewProductInscription_ShouldInactivateAndCreateNewRelation() {

    }

    @Test
    public void givenNotExistingProduct_ThenReturnProductNotFoundException() {

    }
}
