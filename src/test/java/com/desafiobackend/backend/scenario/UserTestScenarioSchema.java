package com.desafiobackend.backend.scenario;

import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.request.UserRequest;

public class UserTestScenarioSchema {

    private User testUser;

    public static User createSpockUser() {
        return User.builder()
                .name("Spock")
                .cpf("794.066.530-87")
                .address("Vulcano Street")
                .email("spock@enterprise")
                .contactNumber("5555-5555")
                .build();
    }

    public static UserRequest createSpockUserRequest() {
        return UserRequest.builder()
                .name("Spock")
                .cpf("794.066.530-87")
                .address("Vulcano Street")
                .email("spock.vulcano@enterprise")
                .contactNumber("9875-5555")
                .build();
    }

    public static UserRequest createUhuraUserRequest() {
        return UserRequest.builder()
                .name("Tenente Uhura")
                .cpf("223.196.222-39")
                .address("United States of Africa Street")
                .email("uhura@enterprise")
                .contactNumber("1234-5432")
                .build();
    }

    public static User createKirkUser() {
        return User.builder()
                .name("James Kirk")
                .cpf("421.001.567-12")
                .address("Enterprise Sky")
                .email("kirk@enterprise")
                .contactNumber("5551-1231")
                .build();
    }

    public static UserRequest createKirkUserRequest() {
        return UserRequest.builder()
                .name("James Kirk")
                .cpf("421.001.567-12")
                .address("Enterprise Sky")
                .email("kirk@enterprise")
                .contactNumber("5551-1231")
                .build();
    }
}
