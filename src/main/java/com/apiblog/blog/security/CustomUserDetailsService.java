package com.apiblog.blog.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import com.apiblog.blog.entity.Rol;
import com.apiblog.blog.entity.Usuario;
import com.apiblog.blog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Override
   public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
      Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
              .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + usernameOrEmail));

      return new User(usuario.getEmail(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
   }

   private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){
      return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
   }

}
