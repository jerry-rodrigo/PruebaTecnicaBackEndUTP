package com.desafioTecnico.security;

import io.jsonwebtoken.security.Keys;

/**
 * Clase que define constantes relacionadas con la seguridad, como la duración de expiración del token JWT y la clave JWT.
 */
public class SecurityConstants {

    /**
     * Duración de expiración del token JWT en milisegundos. Equivalente a 5 minutos.
     */
    public static final long JWT_EXPIRATION_TOKEN = 300000; //equivaler a 5 min, donde 60000 = a 1 min

    /**
     * Clave JWT generada utilizando la utilidad de seguridad.
     */
    public static final byte[] JWT_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512).getEncoded();;
}
