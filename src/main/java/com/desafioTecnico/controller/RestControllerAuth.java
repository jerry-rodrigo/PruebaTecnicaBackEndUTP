package com.desafioTecnico.controller;

import com.desafioTecnico.dtos.DtoAuthResponse;
import com.desafioTecnico.dtos.DtoLogin;
import com.desafioTecnico.dtos.DtoRegister;
import com.desafioTecnico.models.Role;
import com.desafioTecnico.models.User;
import com.desafioTecnico.repositories.IRolRepository;
import com.desafioTecnico.repositories.IUserRepository;
import com.desafioTecnico.security.JwtGenerador;
import java.util.Collections;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para manejar operaciones de autenticación y registro de usuarios.
 */
@Slf4j
@RestController
@RequestMapping("/api/auth/")
public class RestControllerAuth {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private IRolRepository rolesRepository;
    private IUserRepository usuariosRepository;
    private JwtGenerador jwtGenerador;

    @Autowired
    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRolRepository rolesRepository, IUserRepository usuariosRepository, JwtGenerador jwtGenerador) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.usuariosRepository = usuariosRepository;
        this.jwtGenerador = jwtGenerador;
    }

    /**
     * Método para registrar un usuario con el rol "user".
     * @param dtoRegister Datos del usuario a registrar.
     * @return ResponseEntity con mensaje de éxito o error.
     */
    @PostMapping("register")
    public ResponseEntity<String> registrar(@RequestBody DtoRegister dtoRegister) {
        if (usuariosRepository.existsByUsername(dtoRegister.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(dtoRegister.getUsername());
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        Role role = rolesRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));
        usuariosRepository.save(user);
        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
    }

    /**
     * Método para registrar un usuario con el rol "admin".
     * @param dtoRegister Datos del usuario a registrar.
     * @return ResponseEntity con mensaje de éxito o error.
     */
    @PostMapping("registerAdm")
    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegister dtoRegister) {
        if (usuariosRepository.existsByUsername(dtoRegister.getUsername())) {
            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
        }
        Role role = rolesRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("Rol 'ADMIN' no encontrado"));
        User user = new User();
        user.setUsername(dtoRegister.getUsername());
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        user.setRoles(Collections.singletonList(role));
        usuariosRepository.save(user);
        return new ResponseEntity<>("Registro de admin exitoso", HttpStatus.OK);
    }

    /**
     * Método para realizar el inicio de sesión de un usuario y obtener un token JWT.
     * @param dtoLogin Datos de inicio de sesión del usuario.
     * @return ResponseEntity con el token JWT en caso de éxito, o mensaje de error en caso contrario.
     */
    @PostMapping("login")
    public ResponseEntity<DtoAuthResponse> login(@RequestBody DtoLogin dtoLogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthResponse(token), HttpStatus.OK);
    }

}
