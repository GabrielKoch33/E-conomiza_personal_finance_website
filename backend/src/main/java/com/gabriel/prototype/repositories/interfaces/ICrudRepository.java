package com.gabriel.prototype.repositories.interfaces;

import java.util.List;
import java.util.Optional;

public interface ICrudRepository<T> {
    T save(T objeto);

    Optional<T> edit(T objeto);

    Optional<T> deleteById(Long id);

    Optional<T> findById(Long id);

    List<T> listAll();
}
