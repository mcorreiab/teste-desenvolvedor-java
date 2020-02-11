package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.service.ProductApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Api
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductApplicationService productApplicationService;

    @PostMapping("{product}/{userId}")
    @ApiOperation(value = "Relate a product to a user", tags = "User")
    private void relateProduct(@PathVariable("product") final String productName, @PathVariable("userId") final String userId) {
        productApplicationService.relateProduct(productName, userId);
    }

    @GetMapping("{product}")
    @ApiOperation(value = "Find all users wich have a inscription to a product", tags = "User")
    private List<User> findUsersByProduct(@PathVariable("product") final String product) {
        return Collections.unmodifiableList(new ArrayList<>());
    }

    @GetMapping("{userId}")
    @ApiOperation(value = "Find all products of a user", tags = "User")
    private List<Product> listProductsByUser(@PathVariable("userId") final String userId) {
        return Collections.unmodifiableList(new ArrayList<>());
    }


}
