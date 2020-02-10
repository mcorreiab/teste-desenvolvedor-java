package com.desafiobackend.backend.service;

import com.desafiobackend.backend.model.UserProducts;
import com.desafiobackend.backend.repository.UserProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProductApplicationService {

    private final UserProductRepository userProductRepository;

    public void update(final UserProducts userProduct) {
        userProductRepository.save(userProduct);
    }

    public void save(UserProducts userProduct) {
        userProductRepository.insert(userProduct);
    }
}
