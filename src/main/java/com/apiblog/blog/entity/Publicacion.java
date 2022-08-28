package com.apiblog.blog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(
        name = "publicaciones",
        uniqueConstraints = { @UniqueConstraint(columnNames = {"titulo"}) }
)
public class Publicacion {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "titulo", nullable = false)
   private String titulo;

   @Column(name = "descripcion", nullable = false)
   private String descripcion;

   @Column(name = "contenido", nullable = false)
   private String contenido;

   @JsonBackReference
   @OneToMany(
           mappedBy = "publicacion",
           cascade = CascadeType.ALL,
           orphanRemoval = true
   )
   private Set<Comentario> comentarios = new HashSet<>();

}
