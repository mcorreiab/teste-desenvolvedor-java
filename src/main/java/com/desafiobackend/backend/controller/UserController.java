package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.exception.UserNotFoundException;
import com.desafiobackend.backend.mapper.UserRequestMapper;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.request.UserRequest;
import com.desafiobackend.backend.service.UserServicesApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserServicesApplication userServicesApplication;

    private final UserRequestMapper userRequestMapper;

    @GetMapping("/{userId}")
    public User find(final String userId) {
        return userServicesApplication.findUserById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @GetMapping
    public List<User> findAll() {
        return userServicesApplication.findAll();
    }

    @PutMapping("/{userId}/update")
    public User update(final UserRequest userRequest) {
        final User user = userRequestMapper.map(userRequest);
        return userServicesApplication.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(final String userId) {
        userServicesApplication.deleteUser(userId);
    }

    @PostMapping
    public User insert(final UserRequest userRequest) {
        final User newUser = userRequestMapper.map(userRequest);
        return userServicesApplication.insertUser(newUser);
    }
}
