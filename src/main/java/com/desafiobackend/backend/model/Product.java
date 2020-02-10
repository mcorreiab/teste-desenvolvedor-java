package com.desafiobackend.backend.model;

import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Document("products")
public class Product {

    private String name;
}
