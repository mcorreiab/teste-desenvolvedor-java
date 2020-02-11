package com.desafiobackend.backend.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Builder
@Getter
@Document("user_product")
@NoArgsConstructor
@AllArgsConstructor
public class UserProducts {

    private String id;

    private String idUser;

    private String productName;

    private Status status;

    private LocalDateTime inscriptionDateTime;

    private LocalDateTime cancelDateTime;
}
