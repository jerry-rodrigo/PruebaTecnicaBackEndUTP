package com.desafioTecnico.security;

import com.desafioTecnico.models.Role;
import com.desafioTecnico.models.User;
import com.desafioTecnico.repositories.IUserRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio personalizado para cargar detalles de usuario.
 */
@Service
public class CustomUsersDetailsService implements UserDetailsService  {
    private IUserRepository usuariosRepo;

    /**
     * Constructor de la clase CustomUsersDetailsService.
     *
     * @param usuariosRepo Repositorio de usuarios.
     */
    @Autowired
    public CustomUsersDetailsService(IUserRepository usuariosRepo) {
        this.usuariosRepo = usuariosRepo;
    }

    /**
     * Convierte una lista de roles en una colección de autoridades.
     *
     * @param roles Lista de roles.
     * @return Colección de autoridades.
     */
    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles){
        if (roles == null) {
            return Collections.emptyList(); // Devuelve una lista vacía si roles es nulo
        }
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    /**
     * Carga los detalles del usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Detalles del usuario.
     * @throws UsernameNotFoundException Si el usuario no se encuentra.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usuariosRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapToAuthorities(user.getRoles()));
    }
}
