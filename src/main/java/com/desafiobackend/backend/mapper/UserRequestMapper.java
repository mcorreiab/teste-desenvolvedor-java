package com.desafiobackend.backend.mapper;

import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public final class UserRequestMapper implements Mapper<User, UserRequest> {

    @Override
    public User map(final UserRequest userRequest) {
        return User.builder().name(userRequest.getName())
                .cpf(userRequest.getCpf())
                .contactNumber(userRequest.getContactNumber())
                .address(userRequest.getAddress())
                .email(userRequest.getEmail())
                .build();
    }
}
