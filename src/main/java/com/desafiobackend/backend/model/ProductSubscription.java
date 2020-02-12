package com.desafiobackend.backend.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product_subscription")
public class ProductSubscription {

    private String id;

    private String userId;

    private String productName;

    private Status status;

    private LocalDateTime inscriptionDateTime;

    private LocalDateTime cancelDateTime;
}
