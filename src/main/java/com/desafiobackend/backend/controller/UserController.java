package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.exception.ExistingCPFException;
import com.desafiobackend.backend.exception.UserNotFoundException;
import com.desafiobackend.backend.mapper.UserRequestMapper;
import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.request.UserRequest;
import com.desafiobackend.backend.service.UserApplicationService;
import com.desafiobackend.backend.service.UserProductApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Api("Users")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    private final UserRequestMapper userRequestMapper;

    private final UserProductApplicationService userProductApplicationService;

    @GetMapping("{userId}")
    @ApiOperation(value = "Find user by id", tags = "User")
    public User find(@PathVariable("userId") final String userId) {
        return userApplicationService.findUserById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @GetMapping
    @ApiOperation(value = "Find all existing users", tags = "User")
    public List<User> findAll() {
        return userApplicationService.findAll();
    }

    @PutMapping("{userId}/update")
    @ApiOperation(value = "Updates an existing user", tags = "User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User successfully inserted", response = User.class),
            @ApiResponse(code = 404, message = "User Not Found", response = UserNotFoundException.class)
    })
    public User update(@PathVariable("userId") final String userId, @RequestBody final UserRequest userRequest) {
        final User user = userRequestMapper.map(userRequest);
        return userApplicationService.updateUser(userId, user);
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "User has been successfully deleted")
    @ApiOperation(value = "Delete an existing user", tags = "User")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User has been successfully deleted"),
            @ApiResponse(code = 404, message = "User Not Found", response = UserNotFoundException.class)
    })
    public void delete(@PathVariable("userId") final String userId) {
        userApplicationService.deleteUser(userId);
    }

    @PostMapping
    @ApiOperation(value = "Insert a new user", tags = "User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User successfully inserted", response = User.class),
            @ApiResponse(code = 409, message = "Cpf already exists", response = ExistingCPFException.class)
    })
    public User insert(@RequestBody final UserRequest userRequest) {
        final User newUser = userRequestMapper.map(userRequest);
        return userApplicationService.insertUser(newUser);
    }

    @GetMapping("{userId}/products")
    @ApiOperation(value = "Find all products of a user", tags = "Products")
    private List<Product> listProductsByUser(@PathVariable("userId") final String userId) {
        return Collections.unmodifiableList(userProductApplicationService.findAllProductsUser(userId));
    }

}
