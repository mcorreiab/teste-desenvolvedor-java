package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.exception.UserNotFoundException;
import com.desafiobackend.backend.mapper.UserRequestMapper;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.request.UserRequest;
import com.desafiobackend.backend.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    private final UserRequestMapper userRequestMapper;

    @GetMapping("{userId}")
    public User find(@PathVariable("userId") final String userId) {
        return userApplicationService.findUserById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @GetMapping
    public List<User> findAll() {
        return userApplicationService.findAll();
    }

    @PutMapping("{userId}/update")
    public User update(@PathVariable("userId") final UserRequest userRequest) {
        final User user = userRequestMapper.map(userRequest);
        return userApplicationService.updateUser(user);
    }

    @DeleteMapping("{userId}")
    public void delete(@PathVariable("userId") final String userId) {
        userApplicationService.deleteUser(userId);
    }

    @PostMapping
    public User insert(final UserRequest userRequest) {
        final User newUser = userRequestMapper.map(userRequest);
        return userApplicationService.insertUser(newUser);
    }
}
