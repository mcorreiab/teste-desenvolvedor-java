package com.desafiobackend.backend.mapper;

import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.request.UserRequest;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTests {

    @Autowired
    private Mapper<User, UserRequest> mapper = new UserRequestMapper();

    @Test
    public void givenAUserRequest_shouldBeMappedWithSameAttributes() {
        val userRequest = createUserRequest();
        val expectedUser = createUser();
        val userResult = mapper.map(userRequest);
        assertUser(userResult, expectedUser);
    }

    public void assertUser(final User result, final User expected) {
        Assertions.assertThat(result.getName()).isEqualTo(expected.getName());
        Assertions.assertThat(result.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(result.getEmail()).isEqualTo(expected.getEmail());
        Assertions.assertThat(result.getContactNumber()).isEqualTo(expected.getContactNumber());
        Assertions.assertThat(result.getCpf()).isEqualTo(expected.getCpf());
    }

    public UserRequest createUserRequest() {
        return UserRequest.builder()
                .name("Spock")
                .cpf("794.066.530-87")
                .address("Vulcano Street")
                .email("spock@enterprise")
                .contactNumber("5555-5555")
                .build();
    }

    public User createUser() {
        return User.builder()
                .name("Spock")
                .cpf("794.066.530-87")
                .address("Vulcano Street")
                .email("spock@enterprise")
                .contactNumber("5555-5555")
                .build();
    }

}
