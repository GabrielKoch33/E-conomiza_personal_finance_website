package com.gabriel.prototype.repositories.interfaces;

import com.gabriel.prototype.exceptions.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T> {
    void save(T objeto) throws DataAccessException;

    int edit(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;

    Optional<T> findById(Long id) throws DataAccessException;

    List<T> listAll() throws DataAccessException;
}
