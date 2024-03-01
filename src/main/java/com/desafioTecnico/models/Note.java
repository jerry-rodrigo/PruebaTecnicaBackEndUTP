package com.desafioTecnico.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

/**
 * Entidad que representa una nota.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(min = 4, message = "El titulo debe tener al menos 4 caracteres como minimo")
    private String title;

    @NonNull
    private String content;

    private String solicitante;

    @CreatedDate
    private LocalDateTime fechaSolicitud;
}
