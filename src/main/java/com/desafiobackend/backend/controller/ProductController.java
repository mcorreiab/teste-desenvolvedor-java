package com.desafiobackend.backend.controller;

import com.desafiobackend.backend.service.ProductApplicationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

    private final ProductApplicationServices productApplicationServices;

    @PostMapping
    private void relateProduct() {

    }

}
