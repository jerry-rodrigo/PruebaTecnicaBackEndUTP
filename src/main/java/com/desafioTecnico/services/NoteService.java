package com.desafioTecnico.services;

import com.desafioTecnico.models.Note;
import java.util.List;

/**
 * Interfaz para el servicio de gestión de notas.
 */
public interface NoteService {

    /**
     * Obtiene todas las notas.
     * @return Lista de todas las notas.
     */
    List<Note> obtenerTodos();

    /**
     * Obtiene una nota por su ID.
     * @param id El ID de la nota.
     * @return La nota encontrada, o null si no se encuentra.
     */
    Note obtenerPorId(Long id);

    /**
     * Crea una nueva nota.
     * @param note La nota a crear.
     * @return La nota creada.
     */
    Note crearNote(Note note);

    /**
     * Actualiza una nota existente.
     * @param id El ID de la nota a actualizar.
     * @param note La nueva información de la nota.
     * @return La nota actualizada, o null si no se encuentra la nota con el ID especificado.
     */
    Note actualizarNote(Long id, Note note);

    /**
     * Elimina una nota por su ID.
     * @param id El ID de la nota a eliminar.
     */
    void eliminarNote(Long id);
}
