package com.desafiobackend.backend.service;

import com.desafiobackend.backend.IntegrationTests;
import com.desafiobackend.backend.UserEndpoint;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.request.UserRequest;
import com.desafiobackend.backend.scenario.UserTestScenarioSchema;
import io.restassured.RestAssured;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserApplicationServiceTests extends IntegrationTests {

    @Autowired
    private UserApplicationService userApplicationService;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
        testUser = userRepository.insert(UserTestScenarioSchema.createSpockUser());
    }

    @Test
    public void givenAExistingUser_WhenRequestFindById_ThenRetrieveUser() {
        val response = UserEndpoint
                .getUserById(testUser.getId())
                .extract()
                .as(User.class);

        assertUserFieldByField(testUser, response);
    }

    @Ignore
    @Test
    public void givenAExistingUser_WhenRequestAUserUpdate_ThenUpdateUser() {
        val userRequest = UserTestScenarioSchema.createKirkUserRequest();

        val expectedUser = UserEndpoint.insertUser(userRequest)
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(User.class);

        Assertions.assertThat(userRepository.findAll().size()).isEqualTo(2);
        expectedUser.setAddress("new Address");

        val newUserRequest = UserRequest.builder()
                .name(expectedUser.getName())
                .cpf(expectedUser.getCpf())
                .contactNumber(expectedUser.getContactNumber())
                .email(expectedUser.getEmail())
                .address(expectedUser.getAddress())
                .build();

        val responseUser = UserEndpoint.updateUser(expectedUser.getId(), newUserRequest)
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(User.class);

        assertUserFieldByField(responseUser, expectedUser);
    }

    @Ignore
    @Test
    public void givenANonExistingUser_WhenRequestAUserUpdate_ThenReturnUserNotFound() {
        val randomId = "392870278";

        UserEndpoint.updateUser(randomId, UserTestScenarioSchema.createKirkUserRequest())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("User Not Found"));
    }

    @Test
    public void givenAExistingUser_WhenRequestAUserDelete_ThenDeleteUser() {
        val user = userApplicationService.findUserById(testUser.getId());
        Assertions.assertThat(user).isNotNull();

        UserEndpoint.deleteUser(testUser.getId())
                .statusCode(HttpStatus.NO_CONTENT.value());

        val response = UserEndpoint.getAllUsers()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList("$");

        Assertions.assertThat(response.size()).isEqualTo(0);
    }

    @Test
    public void givenANonExistingUser_WhenRequestAUserDelete_ThenReturnUserNotFound() {
        val randomId = "5352434353";
        UserEndpoint.deleteUser(randomId)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("User Not Found"));
    }

    @Test
    public void givenUser_WhenIsInserted_ThenCanBeFound() throws Exception {
        final List<User> expectedUsers = new LinkedList<>();
        expectedUsers.add(testUser);
        val newUser = UserTestScenarioSchema.createKirkUser();
        expectedUsers.add(newUser);

        UserEndpoint.insertUser(UserTestScenarioSchema.createKirkUserRequest())
                .statusCode(HttpStatus.OK.value());

        final List<Map> response = UserEndpoint.getAllUsers()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList("$");

        for (int i = 0; i < response.size(); i++) {
            assertUserFieldByField(expectedUsers.get(i), convertMapToObject(response.get(i)));
        }
    }

    @Test
    public void givenAExistingUser_WhenRequestANewUserWithSameCpf_ThenShouldThrowException() {
        UserEndpoint.insertUser(UserTestScenarioSchema.createSpockUserRequest())
                .statusCode(HttpStatus.CONFLICT.value()).assertThat()
                .body("message", equalTo("User with requested cpf already exists"));
    }

}
