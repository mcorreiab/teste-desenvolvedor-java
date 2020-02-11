package com.desafiobackend.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Builder
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    @NonNull
    private String name;

    @NonNull
    private String cpf;

    @NonNull
    private String address;

    @Nullable
    private String contactNumber;

    @NonNull
    private String email;

    private List<Product> product;
}
