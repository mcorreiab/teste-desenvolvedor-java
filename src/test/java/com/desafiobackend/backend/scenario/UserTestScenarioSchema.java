package com.desafiobackend.backend.scenario;

import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

public final class UserTestScenarioSchema {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    public User createUser() {
        val newUser = User.builder()
                .name("Spock")
                .cpf("794.066.530-87")
                .address("Vulcano Street")
                .email("spock@enterprise")
                .contactNumber("5555-5555")
                .build();

        return userRepository.insert(newUser);
    }
}
