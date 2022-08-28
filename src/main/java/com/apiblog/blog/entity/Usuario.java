package com.apiblog.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(
        name = "usuarios",
        uniqueConstraints = { @UniqueConstraint(columnNames = {"username", "email"}) }
)
public class Usuario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nombre;
   private String username;
   private String email;
   private String password;

   @ManyToMany(
           fetch = FetchType.EAGER,
           cascade = CascadeType.ALL
   )
   @JoinTable(
           name = "usuarios_roles",
           joinColumns = @JoinColumn(
                   name = "usuario_id",
                   referencedColumnName = "id"
           ),
           inverseJoinColumns = @JoinColumn(
                   name = "rol_id",
                   referencedColumnName = "id"
           )
   )
   private Set<Rol> roles = new HashSet<>();

}
