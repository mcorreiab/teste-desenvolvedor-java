package com.desafiobackend.backend.service;

import com.desafiobackend.backend.model.User;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserApplicationServiceTests {

    @Autowired
    private UserApplicationService userApplicationService;

    private User testUser;

    @Before
    public void setup() {
        testUser = createUser();
    }

    @Test
    public void givenAExistingUser_WhenRequestAUserUpdate_ThenUpdateUser() {

    }

    @Test
    public void givenAExistingUser_WhenRequestAUserDelete_ThenDeleteUser() {

    }

    @Test
    public void givenUser_WhenIsInserted_ThenCanBeFound() {
        final User user = userApplicationService.findUserById(testUser.getId()).orElse(null);
        assertUser(user);
    }

    @Test
    public void givenAExistingUser_WhenRequestANewUserWithSameCpf_ThenShouldThrowException() {

    }

    public void assertUser(final User user) {
        Assertions.assertThat(testUser.getName()).isEqualTo(user.getName());
        Assertions.assertThat(testUser.getAddress()).isEqualTo(user.getAddress());
        Assertions.assertThat(testUser.getEmail()).isEqualTo(user.getEmail());
        Assertions.assertThat(testUser.getContactNumber()).isEqualTo(user.getContactNumber());
        Assertions.assertThat(testUser.getCpf()).isEqualTo(user.getCpf());
    }

    public User createUser() {
        val newUser = User.builder()
                .name("Spock")
                .cpf("794.066.530-87")
                .address("Vulcano Street")
                .email("spock@enterprise")
                .contactNumber("5555-5555")
                .build();

        return userApplicationService.insertUser(newUser);
    }
}
