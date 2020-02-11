package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.service.ProductApplicationService;
import com.desafiobackend.backend.service.UserProductApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Api("Products")
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductSubscriptionController {

    private final ProductApplicationService productApplicationService;

    private final UserProductApplicationService userProductApplicationService;

    @PostMapping("{productName}/{userId}")
    @ApiOperation(value = "Relate a product to a user", tags = "Products")
    private void relateProduct(@PathVariable("productName") final String productName, @PathVariable("userId") final String userId) {
        productApplicationService.relateProduct(productName, userId);
    }

    @GetMapping("{producproductNamet}/user")
    @ApiOperation(value = "Find all users wich have a inscription to a product", tags = "Products")
    private List<User> findUsersByProduct(@PathVariable("productName") final String productName) {
        return Collections.unmodifiableList(userProductApplicationService.findUsersOfProduct(productName));
    }

}
