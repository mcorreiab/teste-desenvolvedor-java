package com.desafiobackend.backend.mapper;

@FunctionalInterface
public interface Mapper<T,D> {

    T map(D request);
}
