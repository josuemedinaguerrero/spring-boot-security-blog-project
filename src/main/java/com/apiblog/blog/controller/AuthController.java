package com.apiblog.blog.controller;

import com.apiblog.blog.security.JWTAuthResponseDTO;
import com.apiblog.blog.dto.LoginDTO;
import com.apiblog.blog.dto.RegistroDTO;
import com.apiblog.blog.entity.Rol;
import com.apiblog.blog.entity.Usuario;
import com.apiblog.blog.repository.RolRepository;
import com.apiblog.blog.repository.UsuarioRepository;
import com.apiblog.blog.security.JwtTokenProvider;
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

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Autowired
   private RolRepository rolRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   private JwtTokenProvider jwtTokenProvider;

   @PostMapping("/login")
   public ResponseEntity<JWTAuthResponseDTO> authenticateUser( @RequestBody LoginDTO loginDTO ) {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              loginDTO.getUsernameOrEmail(),
              loginDTO.getPassword()
      ));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = jwtTokenProvider.generarToken( authentication );
      return new ResponseEntity<>(new JWTAuthResponseDTO(token), HttpStatus.OK);
   }

   @PostMapping("/register")
   public ResponseEntity<?> registrarUsuario( @RequestBody RegistroDTO registroDTO ) {
      if (usuarioRepository.existsByUsername(registroDTO.getUsername())) {
         return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
      }
      if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
         return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
      }
      Usuario usuario = new Usuario();
      usuario.setNombre( registroDTO.getNombre() );
      usuario.setUsername( registroDTO.getUsername() );
      usuario.setEmail( registroDTO.getEmail() );
      usuario.setPassword(passwordEncoder.encode( registroDTO.getPassword() ));

      Rol roles = rolRepository.findByNombre("ROLE_ADMIN").get();
      usuario.setRoles(Collections.singleton( roles ));

      usuarioRepository.save( usuario );
      return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
   }

}
