package com.desafiobackend.backend.service;

import com.desafiobackend.backend.exception.ExistingCPFException;
import com.desafiobackend.backend.exception.UserNotFoundException;
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
        this.userExists(userId);
        return userRepository.findById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(final String userId, final User userRequest) {
        this.userExists(userId);
        return userRepository.save(userRequest);
    }

    public void deleteUser(final String userId) {
        this.userExists(userId);
        userRepository.deleteById(userId);
    }

    public User insertUser(final User userRequest) {
        if (userRepository.findUserByCPF(userRequest.getCpf()).isPresent()) {
            throw new ExistingCPFException();
        }
        return userRepository.insert(userRequest);
    }

    public List<User> findAllById(List<String> users) {
        return userRepository.findeUsersIn(users);
    }

    private void userExists(final String userId) {
        if (!userRepository.findById(userId).isPresent()) {
            throw new UserNotFoundException();
        }
    }
}
