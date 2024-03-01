package com.desafioTecnico.impl;

import com.desafioTecnico.models.Note;
import com.desafioTecnico.repositories.INoteRepository;
import com.desafioTecnico.services.NoteService;
import com.desafioTecnico.services.impl.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NoteServiceImplTest {

    private NoteService noteService;

    @Mock
    private INoteRepository noteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        noteService = new NoteServiceImpl(noteRepository);
    }

    @Test
    public void testObtenerTodos() {
        Note note1 = new Note();
        Note note2 = new Note();
        List<Note> expectedNotes = Arrays.asList(note1, note2);

        when(noteRepository.findAll()).thenReturn(expectedNotes);

        List<Note> actualNotes = noteService.obtenerTodos();

        assertEquals(expectedNotes, actualNotes);
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerPorId_NotaExistente() {
        Long noteId = 1L;
        Note expectedNote = new Note();
        expectedNote.setId(noteId);

        when(noteRepository.findById(noteId)).thenReturn(Optional.of(expectedNote));

        Note actualNote = noteService.obtenerPorId(noteId);

        assertEquals(expectedNote, actualNote);
        verify(noteRepository, times(1)).findById(noteId);
    }

    @Test
    public void testObtenerPorId_NotaNoExistente() {
        Long noteId = 1L;

        when(noteRepository.findById(noteId)).thenReturn(Optional.empty());

        Note actualNote = noteService.obtenerPorId(noteId);

        assertNull(actualNote);
        verify(noteRepository, times(1)).findById(noteId);
    }

    @Test
    public void testCrearNote() {
        Note note = new Note();
        Note expectedNote = new Note();
        expectedNote.setId(1L);

        when(noteRepository.save(note)).thenReturn(expectedNote);

        Note actualNote = noteService.crearNote(note);

        assertEquals(expectedNote, actualNote);
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    public void testEliminarNote() {
        Long noteId = 1L;

        noteService.eliminarNote(noteId);

        verify(noteRepository, times(1)).deleteById(noteId);
    }
}