package com.desafioTecnico.controller;

import com.desafioTecnico.models.Note;
import com.desafioTecnico.services.NoteService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Descripción de la clase NoteController.
 * Esta clase maneja las solicitudes relacionadas con las notas.
 */
@RestController
@RequestMapping("/api/note")
public class NoteController {

    /**
     * Servicio de notas.
     */
    @Autowired
    private NoteService noteService;

    /**
     * Obtiene todos los tipos de cambio y agrega el nombre de usuario actual a cada registro antes de devolverlos.
     * @return Lista de notas con el nombre de usuario asociado.
     */
    @GetMapping("/listar")
    public List<Note> obtenerNote() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();

            List<Note> notes = noteService.obtenerTodos();
            notes.forEach(tc -> tc.setSolicitante(username));

            return notes;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene un tipo de cambio por su ID y agrega el nombre de usuario actual antes de devolverlo.
     * @param id ID del tipo de cambio a obtener.
     * @return El tipo de cambio con el nombre de usuario asociado.
     */
    @GetMapping("listarId/{id}")
    public Note obtenerNotePorId(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Note note = noteService.obtenerPorId(id);

            if (!note.getSolicitante().equals(username)) {
                throw new AccessDeniedException("No tiene permiso para acceder a esta nota.");
            }

            note.setSolicitante(username);
            return note;
        } else {
            throw new AccessDeniedException("No tiene permiso para acceder a esta nota.");
        }
    }

    /**
     * Crea un nuevo tipo de cambio, establece el nombre de usuario actual y la fecha de solicitud, y luego lo devuelve.
     * @param note La nota a crear.
     * @return La nota creada.
     */
    @PostMapping("/crear")
    public Note crearNote(@RequestBody Note note) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();

            note.setSolicitante(username);
            note.setFechaSolicitud(LocalDateTime.now());
            return noteService.crearNote(note);
        } else {
            throw new AccessDeniedException("No tiene permiso para crear una nota.");
        }
    }

    /**
     * Actualiza un tipo de cambio existente por su ID, establece el nombre de usuario actual y devuelve el tipo de cambio actualizado.
     * @param id ID del tipo de cambio a actualizar.
     * @param note La información actualizada del tipo de cambio.
     * @return El tipo de cambio actualizado.
     */
    @PutMapping("actualizar/{id}")
    public Note actualizarNote(@PathVariable Long id, @RequestBody Note note) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();

            Note existingNote = noteService.obtenerPorId(id);
            if (!existingNote.getSolicitante().equals(username)) {
                throw new AccessDeniedException("No tiene permiso para actualizar esta nota.");
            }

            note.setSolicitante(username);
            return noteService.actualizarNote(id, note);
        } else {
            throw new AccessDeniedException("No tiene permiso para actualizar una nota.");
        }
    }

    /**
     * Elimina un tipo de cambio por su ID.
     * @param id ID del tipo de cambio a eliminar.
     */
    @DeleteMapping("eliminar/{id}")
    public void eliminarNote(@PathVariable Long id) {
        noteService.eliminarNote(id);
    }
}