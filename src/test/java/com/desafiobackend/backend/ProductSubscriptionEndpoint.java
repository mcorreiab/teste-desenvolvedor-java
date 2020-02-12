package com.desafiobackend.backend;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public final class ProductSubscriptionEndpoint {

    public static ValidatableResponse relateProduct(final String productName, final String userId) {
        return given()
                .pathParam("productName", productName)
                .pathParam("userId", userId)
                .when()
                .post("/products/{productName}/{userId}").peek()
                .then();
    }

    public static ValidatableResponse listProductSubscribers(final String productName) {
        return given()
                .queryParam("productName", productName)
                .when()
                .get("/products")
                .then();
    }

    public static ValidatableResponse listAllUsers() {
        return given()
                .when()
                .get("/products")
                .then();
    }

    public static ValidatableResponse listUserProductSubscriptions(final String userId) {
        return given()
                .pathParam("userId", userId)
                .when()
                .get("/products/{userId}/subscription")
                .then();
    }

}
