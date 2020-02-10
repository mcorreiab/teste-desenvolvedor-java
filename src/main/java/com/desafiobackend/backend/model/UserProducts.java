package com.desafiobackend.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("userproducts")
public class UserProducts {

    private String idUser;

    private String idProduct;
}
