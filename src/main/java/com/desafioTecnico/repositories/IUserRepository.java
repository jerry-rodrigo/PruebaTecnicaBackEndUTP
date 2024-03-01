package com.desafioTecnico.repositories;

import com.desafioTecnico.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad User.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario en la base de datos.
     *
     * @param username El nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario encontrado, o un Optional vacío si no se encuentra.
     */
    Optional<User> findByUsername(String username);

    /**
     * Verifica si un usuario con el nombre de usuario dado existe en la base de datos.
     *
     * @param username El nombre de usuario a verificar.
     * @return true si el usuario existe, false de lo contrario.
     */
    Boolean existsByUsername(String username);
}
