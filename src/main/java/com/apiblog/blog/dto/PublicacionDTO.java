package com.apiblog.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data @AllArgsConstructor @NoArgsConstructor
public class PublicacionDTO {

   private Long id;

//   @NotEmpty(message = "El campo no debe estar vacío")
   @Size(min = 10, message = "Debe de contener mínimo 10 caracteres")
   private String titulo;

//   @NotEmpty(message = "El campo no debe estar vacío")
   @Size(min = 20, message = "Debe de contener mínimo 20 caracteres")
   private String descripcion;

//   @NotEmpty(message = "El campo no debe estar vacío")
   @Size(min = 10, message = "Debe de contener mínimo 10 caracteres")
   private String contenido;

}
