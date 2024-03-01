package com.desafioTecnico.dtos;

import lombok.Data;

/**
 * DTO para la respuesta de autenticación.
 */
@Data
public class DtoAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";

    /**
     * Constructor para crear una nueva respuesta de autenticación con el token de acceso.
     * @param accessToken El token de acceso generado.
     */
    public DtoAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
