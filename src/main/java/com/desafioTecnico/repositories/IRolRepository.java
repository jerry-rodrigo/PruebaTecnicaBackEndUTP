package com.desafioTecnico.repositories;

import com.desafioTecnico.models.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Role.
 */
@Repository
public interface IRolRepository extends JpaRepository<Role, Long> {

    /**
     * Busca un role por su nombre en la base de datos.
     *
     * @param name El nombre del role a buscar.
     * @return Un Optional que contiene el role encontrado, o un Optional vacío si no se encuentra.
     */
    Optional<Role> findByName(String name);
}
