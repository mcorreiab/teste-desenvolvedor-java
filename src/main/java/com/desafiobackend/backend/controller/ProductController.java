package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.service.ProductApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductApplicationService productApplicationService;

    @PostMapping("{product}/{userId}")
    private void relateProduct(@PathVariable("product") final String productName, @PathVariable("userId") final String userId) {
        productApplicationService.relateProduct(productName, userId);
    }

    @GetMapping("{product}")
    private List<User> findUsersByProduct(@PathVariable("product") final String product) {
        return Collections.unmodifiableList(new ArrayList<>());
    }

    @GetMapping("{userId}")
    private List<Product> listProductsByUser(@PathVariable("userId") final String userId) {
        return Collections.unmodifiableList(new ArrayList<>());
    }


}
