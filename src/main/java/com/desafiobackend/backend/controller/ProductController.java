package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.model.Product;
import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.service.ProductApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductApplicationService productApplicationService;

    @PostMapping("/{product}/{userId}")
    private void relateProduct(final Product product, final String userId) {
        productApplicationService.relateProduct(product, userId);
    }

    @GetMapping("/{product}")
    private List<User> findUsersByProduct(final String product) {
        return Collections.unmodifiableList(new ArrayList<>());
    }

    @GetMapping
    private List<Product> listProductsByUser() {
        return Collections.unmodifiableList(new ArrayList<>());
    }


}
