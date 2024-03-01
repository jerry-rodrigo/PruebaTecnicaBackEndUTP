package com.desafioTecnico.dtos;

import lombok.Data;

/**
 * DTO para las credenciales de inicio de sesión.
 */
@Data
public class DtoLogin {
    private String username;
    private String password;
}
