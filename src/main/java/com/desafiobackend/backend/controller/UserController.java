package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.exception.ExistingCPFException;
import com.desafiobackend.backend.exception.UserNotFoundException;
import com.desafiobackend.backend.mapper.UserRequestMapper;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.request.UserRequest;
import com.desafiobackend.backend.service.UserApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    private final UserRequestMapper userRequestMapper;

    @GetMapping("{userId}")
    @ApiOperation(value = "Find user by id")
    public User find(@PathVariable("userId") final String userId) {
        return userApplicationService.findUserById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @GetMapping
    @ApiOperation(value = "Find all existing users")
    public List<User> findAll() {
        return userApplicationService.findAll();
    }

    @PutMapping("{userId}/update")
    @ApiOperation(value = "Updates an existing user")
    public User update(@PathVariable("userId") final String userId, final UserRequest userRequest) {
        final User user = userRequestMapper.map(userRequest);
        return userApplicationService.updateUser(user);
    }

    @DeleteMapping("{userId}")
    @ApiOperation(value = "Delete an existing user")
    public void delete(@PathVariable("userId") final String userId) {
        userApplicationService.deleteUser(userId);
    }

    @PostMapping
    @ApiOperation(value = "Insert a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User successfully inserted", response = User.class),
            @ApiResponse(code = 409, message = "Cpf already exists", response = ExistingCPFException.class)
    })
    public User insert(final UserRequest userRequest) {
        final User newUser = userRequestMapper.map(userRequest);
        return userApplicationService.insertUser(newUser);
    }
}
