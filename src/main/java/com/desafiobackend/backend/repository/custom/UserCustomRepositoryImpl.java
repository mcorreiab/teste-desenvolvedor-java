package com.desafiobackend.backend.repository.custom;

import com.desafiobackend.backend.model.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCustomRepositoryImpl extends AbstractCustomRepository implements UserCustomRepository {

    @Override
    public Optional<User> findUserByCPF(String userCpf) {
        final Query query = createQuery(userCpf);
        final User queryResult = mongoTemplate.findOne(query, User.class);
        return Optional.ofNullable(queryResult);
    }

    private Query createQuery(final String userCpf) {
        return new Query().addCriteria(createCriteria(userCpf));
    }

    private Criteria createCriteria(final String userCpf) {
        return Criteria.where("Cpf").is(userCpf);
    }
}
