package com.apiblog.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data @AllArgsConstructor @NoArgsConstructor
public class ComentarioDTO {

   private Long id;

//   @NotEmpty(message = "El campo no debe estar vacío")
   @Size(min = 5, message = "Debe de contener mínimo 5 caracteres")
   private String nombre;

   @NotEmpty(message = "El campo no debe estar vacío")
   @Email(message = "Debe ser un email válido")
   private String email;

   @NotEmpty(message = "El campo no debe estar vacío")
//   @Size(min = 10, message = "Debe de contener mínimo 10 caracteres")
   private String cuerpo;

}
