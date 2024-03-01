package com.desafioTecnico.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * DTO para las solicitudes relacionadas con las notas.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoNoteRequest {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
    private String solicitante;
    private LocalDateTime fechaSolicitud;
}
