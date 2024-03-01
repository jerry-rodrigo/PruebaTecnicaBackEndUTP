package com.desafioTecnico.controller;

import com.desafioTecnico.models.Note;
import com.desafioTecnico.services.NoteService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NoteControllerTest {

    @InjectMocks
    private NoteController noteController;

    @Mock
    private NoteService noteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ROLE_USER")
    public void testObtenerNote() {
        // Mock del usuario autenticado
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Mock de los datos de prueba
        List<Note> notes = new ArrayList<>();
        Note note1 = new Note();
        note1.setId(1L);
        note1.setTitle("Nota 1");
        note1.setContent("Contenido de la nota 1");
        note1.setSolicitante("testuser");
        note1.setFechaSolicitud(LocalDateTime.now());
        notes.add(note1);

        // Llamada al método del controlador
        List<Note> result = noteController.obtenerNote();

        // Verificación de los resultados
        assertNotNull("La lista de notas devuelta por el controlador es null", result);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ROLE_ADMIN") // Simula un usuario autenticado con el rol ROLE_ADMIN
    public void testEliminarNote() {
        // Mock del ID de la nota
        Long noteId = 1L;

        // Llamada al método del controlador
        noteController.eliminarNote(noteId);

        // Verificación de que el método del servicio fue llamado con el ID correcto
        verify(noteService, times(1)).eliminarNote(noteId);
    }
}