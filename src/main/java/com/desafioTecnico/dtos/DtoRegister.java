package com.desafioTecnico.dtos;

import lombok.Data;

/**
 * DTO para el registro de usuarios.
 */
@Data
public class DtoRegister {
    private String username;
    private String password;
}
