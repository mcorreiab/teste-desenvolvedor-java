package com.desafiobackend.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User with requested cpf already exists")
public class ExistingCPFException extends ApplicationsException {

    public ExistingCPFException() {
        super();
    }
}
