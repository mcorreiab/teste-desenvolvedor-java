package com.desafiobackend.backend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

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
}

