package com.desafioTecnico.services.impl;

import com.desafioTecnico.models.Note;
import com.desafioTecnico.repositories.INoteRepository;
import com.desafioTecnico.services.NoteService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio NoteService.
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final INoteRepository iNoteRepository;

    @Autowired
    public NoteServiceImpl(INoteRepository iNoteRepository) {
        this.iNoteRepository = iNoteRepository;
    }

    /**
     * Obtiene todas las notas.
     * @return Lista de todas las notas.
     */
    @Override
    public List<Note> obtenerTodos() {
        return iNoteRepository.findAll();
    }

    /**
     * Obtiene una nota por su ID.
     * @param id El ID de la nota.
     * @return La nota encontrada, o null si no se encuentra.
     */
    @Override
    public Note obtenerPorId(Long id) {
        return iNoteRepository.findById(id).orElse(null);
    }

    /**
     * Crea una nueva nota.
     * @param note La nota a crear.
     * @return La nota creada.
     */
    @Override
    public Note crearNote(Note note) {
        return iNoteRepository.save(note);
    }

    /**
     * Actualiza una nota existente.
     * @param id El ID de la nota a actualizar.
     * @param note La nueva información de la nota.
     * @return La nota actualizada, o una nota vacía si no se encuentra la nota con el ID especificado.
     */
    @Override
    public Note actualizarNote(Long id, Note note) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (iNoteRepository.existsById(id)) {
            note.setSolicitante(username);
            note.setFechaSolicitud(LocalDateTime.now());
            return iNoteRepository.save(note);
        } else {
            return createEmpty();
        }
    }

    /**
     * Elimina una nota por su ID.
     * @param id El ID de la nota a eliminar.
     */
    @Override
    public void eliminarNote(Long id) {
        iNoteRepository.deleteById(id);
    }

    /**
     * Crea una nota vacía con ID -1.
     * @return La nota vacía creada.
     */
    public static Note createEmpty() {
        Note note = new Note();
        note.setId(-1L);
        return note;
    }
}
