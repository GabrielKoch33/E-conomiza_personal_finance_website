package com.gabriel.prototype.repositories.interfaces;

import com.gabriel.prototype.exceptions.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T> {
    void save(T objeto) throws DataAccessException;

    Optional<T> edit(T objeto);

    Optional<T> deleteById(Long id);

    Optional<T> findById(Long id);

    List<T> listAll();
}
