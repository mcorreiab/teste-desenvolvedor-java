package com.desafiobackend.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    private String id;

    private String name;

    private LocalDateTime dateTime;
}
