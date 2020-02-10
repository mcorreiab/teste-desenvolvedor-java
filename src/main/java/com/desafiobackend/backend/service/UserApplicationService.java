package com.desafiobackend.backend.service;

import com.desafiobackend.backend.model.User;
import com.desafiobackend.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;

    public Optional<User> findUserById(final String userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(final User userRequest) {
        return userRepository.save(userRequest);
    }

    public void deleteUser(final String userId) {
        userRepository.deleteById(userId);
    }

    public User insertUser(final User userRequest) {
        return null;
    }
}
