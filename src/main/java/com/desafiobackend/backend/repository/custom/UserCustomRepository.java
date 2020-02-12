package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserCustomRepository {

    Optional<User> findUserByCPF(final String userCpf);

    List<User> findeUsersIn(final List<String> users);
}
