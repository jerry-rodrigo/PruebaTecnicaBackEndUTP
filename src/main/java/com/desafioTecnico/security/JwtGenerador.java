package com.desafioTecnico.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de generar y validar tokens JWT.
 */
@Component
public class JwtGenerador {

    /**
     * Genera un token JWT utilizando la autenticación proporcionada.
     *
     * @param authentication La autenticación del usuario.
     * @return El token JWT generado.
     */
    public String generarToken(Authentication authentication) {

        String username = authentication.getName();
        Date tiempoActual = new Date();
        Date expiracionToken = new Date(tiempoActual.getTime() + SecurityConstants.JWT_EXPIRATION_TOKEN);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiracionToken)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_KEY)
                .compact();
        System.out.println("Fecha de expiración del token: " + expiracionToken);
        return token;
    }

    /**
     * Obtiene el nombre de usuario a partir de un token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario.
     */
    public String obtenerUsernameDeJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Valida un token JWT.
     *
     * @param token El token JWT a validar.
     * @return true si el token es válido, false de lo contrario.
     * @throws AuthenticationCredentialsNotFoundException Si el token ha expirado o es incorrecto.
     */
    public Boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationCredentialsNotFoundException("Jwt ah expirado o esta incorrecto");
        }
    }
}
