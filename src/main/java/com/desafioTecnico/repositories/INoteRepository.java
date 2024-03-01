package com.desafioTecnico.repositories;

import java.util.List;
import com.desafioTecnico.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Note.
 */
@Repository
public interface INoteRepository extends JpaRepository<Note, Long> {
    List<Note> findBySolicitante(String solicitante);
}
