package com.desafiobackend.backend;

import com.desafiobackend.backend.model.User;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public final class UserEndpoint {

    public static ValidatableResponse getUserById(final String userId) {
        return given()
                .param("userId", userId)
                .when()
                .get("/users/{userId}")
                .then();
    }

    public static ValidatableResponse updateUser(final String userId, final User user) {
        return given().param("userId", userId)
                .body(user)
                .when()
                .put("/users/{userId}/update")
                .then();
    }

    public static ValidatableResponse deleteUser(final String userId) {
        return given()
                .param("userId", userId)
                .when()
                .delete("/users/{userId}")
                .then();
    }

    public static ValidatableResponse insertUser(final User user) {
        return given()
                .body(user)
                .when()
                .post("/users")
                .then();
    }
}
