package com.apiblog.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "comentarios")
public class Comentario {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nombre;
   private String email;
   private String cuerpo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "publicacion_id", nullable = false)
   private Publicacion publicacion;

}
