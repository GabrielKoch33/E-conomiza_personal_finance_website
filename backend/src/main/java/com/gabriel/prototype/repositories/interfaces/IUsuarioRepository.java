package com.gabriel.prototype.repositories.interfaces;

import com.gabriel.prototype.models.Usuario;

import java.util.Optional;

/**
 * De Interface para Interface se usa a palavra 'extends'.
 * <br><br>
 * Implementação = tornar default (opcional).
 * Sobrescrever = tornar obrigatório.
 * <br><br>
 * Interfaces quando extender outra não precisam sobrescrever os métodos, nesse caso
 * a interface 'intermediária' acumula os métodos herdados juntamente com os seus.
 * **/
public interface IUsuarioRepository extends ICrudRepository<Usuario> {
    Optional<Usuario> findByUsername(String username);
}
