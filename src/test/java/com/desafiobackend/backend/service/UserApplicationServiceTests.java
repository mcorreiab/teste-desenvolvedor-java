package com.desafiobackend.backend.service;

import com.desafiobackend.backend.IntegrationTests;
import com.desafiobackend.backend.UserEndpoint;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.repository.UserRepository;
import com.desafiobackend.backend.request.UserRequest;
import io.restassured.RestAssured;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
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

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        RestAssured.port = port;
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
        val updateUser = createUserRequest();

        val response = UserEndpoint.updateUser(testUser.getId(), updateUser)
                .extract()
                .body()
                .as(User.class);

//        assertUserFieldByField(updateUser, response);
    }

    @Test
    public void givenANonExistingUser_WhenRequestAUserUpdate_ThenReturnUserNotFound() {
        val randomId = "392870278";
        val updateUser = createUserRequest();

        UserEndpoint.updateUser(randomId, updateUser)
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
        val newUser = User.builder()
                .name("James Kirk")
                .cpf("421.001.567-12")
                .address("Enterprise Sky")
                .email("kirk@enterprise")
                .contactNumber("5551-1231")
                .build();
        expectedUsers.add(newUser);

        UserEndpoint.insertUser(newUser)
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
        UserEndpoint.insertUser(testUser)
                .statusCode(HttpStatus.CONFLICT.value()).assertThat()
                .body("message", equalTo("User with requested cpf already exists"));
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

    private UserRequest createUserRequest() {
        return UserRequest.builder()
                .name("Tenente Uhura")
                .cpf("223.196.222-39")
                .address("United States of Africa Street")
                .email("uhura@enterprise")
                .contactNumber("1234-5432")
                .build();
    }

    @After
    public void clean() {
        userRepository.deleteAll();
    }
}
