package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.service.ProductSubscriptionApplicationService;
import com.desafiobackend.backend.service.SubscribeProductApplicationService;
import com.desafiobackend.backend.service.UserSubscriptionsApplicationService;
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

    private final SubscribeProductApplicationService subscribeProductApplicationService;

    private final ProductSubscriptionApplicationService productSubscriptionApplicationService;

    private final UserSubscriptionsApplicationService userSubscriptionsApplicationService;

    @PostMapping("{productName}/{userId}")
    @ApiOperation(value = "Relate a product to a user", tags = "Products")
    private void relateProduct(@PathVariable("productName") final String productName, @PathVariable("userId") final String userId) {
        subscribeProductApplicationService.relateProduct(productName, userId);
    }

    @GetMapping
    @ApiOperation(value = "Find all users wich have a subscription to a product", tags = "Products")
    private List<User> listProductSubscribers(@RequestParam(value = "productName", required = false) final String productName) {
        return Collections.unmodifiableList(productSubscriptionApplicationService.findUsersOfProduct(productName));
    }

    @GetMapping("{userId}/subscription")
    @ApiOperation(value = "Find all product subscriptions of a user", tags = "Products")
    private List<Product> listUserProductSubscriptions(@PathVariable("userId") final String userId) {
        return Collections.unmodifiableList(userSubscriptionsApplicationService.findUserProductSubscriptions(userId));
    }

}
