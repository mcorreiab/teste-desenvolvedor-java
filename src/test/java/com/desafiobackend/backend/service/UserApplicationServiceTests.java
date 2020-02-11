package com.desafiobackend.backend.service;

import com.desafiobackend.backend.UserEndpoint;
import com.desafiobackend.backend.model.User;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    public void givenAExistingUser_WhenRequestFindById_ThenRetrieveUser() {
        val response = UserEndpoint
                .getUserById(testUser.getId())
                .extract()
                .as(User.class);

        assertUserFieldByField(testUser, response);
    }

    @Test
    public void givenAExistingUser_WhenRequestAUserUpdate_ThenUpdateUser() {
        val updateUser = User.builder()
                .name("Spock")
                .cpf("794.066.530-87")
                .address("Vulcano Street")
                .email("spock.vulcano@enterprise")
                .contactNumber("5432-3412")
                .build();

        val response = UserEndpoint.updateUser(testUser.getId(), updateUser)
                .extract()
                .body()
                .as(User.class);

        assertUserFieldByField(updateUser, response);
    }

    @Test
    public void givenANonExistingUser_WhenRequestAUserUpdate_ThenReturnUserNotFound() {
        val randomId = "392870278";
        UserEndpoint.updateUser(randomId, testUser)
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void givenAExistingUser_WhenRequestAUserDelete_ThenDeleteUser() {
        UserEndpoint.deleteUser(testUser.getId())
                .statusCode(HttpStatus.OK.value());

        val user = userApplicationService.findUserById(testUser.getId()).orElse(null);
        Assertions.assertThat(user).isNull();
    }

    @Test
    public void givenUser_WhenIsInserted_ThenCanBeFound() {
        UserEndpoint.insertUser(testUser);

        final User user = userApplicationService.findUserById(testUser.getId()).orElse(null);
        assertUserFieldByField(testUser, user);
    }

    @Test
    public void givenAExistingUser_WhenRequestANewUserWithSameCpf_ThenShouldThrowException() {
        UserEndpoint.insertUser(testUser).statusCode(HttpStatus.CONFLICT.value());
    }

    public void assertUserFieldByField(final User expectedUser, final User resultUser) {
        Assertions.assertThat(expectedUser.getName()).isEqualTo(resultUser.getName());
        Assertions.assertThat(expectedUser.getAddress()).isEqualTo(resultUser.getAddress());
        Assertions.assertThat(expectedUser.getEmail()).isEqualTo(resultUser.getEmail());
        Assertions.assertThat(expectedUser.getContactNumber()).isEqualTo(resultUser.getContactNumber());
        Assertions.assertThat(expectedUser.getCpf()).isEqualTo(resultUser.getCpf());
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
