package com.desafiobackend.backend;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public final class ProductEndpoint {

    public static ValidatableResponse relateProduct(final String productName, final String userId) {
        return given()
                .pathParam("product", productName)
                .pathParam("userId", userId)
                .when()
                .post("/products/{product}/{userId}")
                .then();
    }

    public static ValidatableResponse findUsersByProduct(final String product) {
        return given()
                .pathParam("product", product)
                .when()
                .get("/products/{product}")
                .then();
    }

    public static ValidatableResponse listProductsByUser(final String userId) {
        return given()
                .pathParam("userId", userId)
                .when()
                .get("/products/{userId}")
                .then();
    }


}
