package com.desafioTecnico.implAcceptance;

import com.desafioTecnico.models.Note;
import com.desafioTecnico.repositories.INoteRepository;
import com.desafioTecnico.services.impl.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTestAcceptance {

    @Mock
    private INoteRepository noteRepository;

    @InjectMocks
    private NoteServiceImpl noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerTodos() {
        // Arrange
        Note note1 = new Note(1L, "Note 1", "Content 1", "User 1", LocalDateTime.now());
        Note note2 = new Note(2L, "Note 2", "Content 2", "User 2", LocalDateTime.now());
        when(noteRepository.findAll()).thenReturn(Arrays.asList(note1, note2));

        // Act
        List<Note> notes = noteService.obtenerTodos();

        // Assert
        assertEquals(2, notes.size());
        assertEquals("Note 1", notes.get(0).getTitle());
        assertEquals("Content 1", notes.get(0).getContent());
        assertEquals("Note 2", notes.get(1).getTitle());
        assertEquals("Content 2", notes.get(1).getContent());
    }

    @Test
    void obtenerPorId_Existente() {
        // Arrange
        Long id = 1L;
        Note note = new Note(id, "Note 1", "Content 1", "User 1", LocalDateTime.now());
        when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        // Act
        Note result = noteService.obtenerPorId(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Note 1", result.getTitle());
        assertEquals("Content 1", result.getContent());
    }

    @Test
    void obtenerPorId_NoExistente() {
        // Arrange
        Long id = 1L;

        // Act
        Note result = noteService.obtenerPorId(id);

        // Assert
        assertNull(result);
    }

    @Test
    void crearNote() {
        // Arrange
        Note note = new Note(null, "New Note", "New Content", "User 1", LocalDateTime.now());
        when(noteRepository.save(note)).thenReturn(note);

        // Act
        Note result = noteService.crearNote(note);

        // Assert
        assertNotNull(result);
        assertEquals("New Note", result.getTitle());
        assertEquals("New Content", result.getContent());
        assertEquals("User 1", result.getSolicitante());
        assertNotNull(result.getFechaSolicitud());
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void eliminarNote() {
        // Arrange
        Long id = 1L;

        // Act
        noteService.eliminarNote(id);

        // Assert
        verify(noteRepository, times(1)).deleteById(id);
    }

}
