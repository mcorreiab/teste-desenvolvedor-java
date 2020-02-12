package com.desafiobackend.backend.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NonNull
    private String name;

    @NonNull
    private String cpf;

    @NonNull
    private String address;

    @Nullable
    @JsonProperty("contact_number")
    private String contactNumber;

    @NonNull
    private String email;
}

