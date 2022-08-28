package com.apiblog.blog.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Data
public class ResourceNotFoundException extends RuntimeException {

   private String nombreDelRecurso;
   private String nombreDelCampo;
   private Long valorDelCampo;

   public ResourceNotFoundException( String nombreDelRecurso, String nombreDelCampo, Long valorDelCampo ) {
      super(String.format("%s no encontrado(a) con %s: %s", nombreDelRecurso, nombreDelCampo, valorDelCampo));
      this.nombreDelRecurso = nombreDelRecurso;
      this.nombreDelCampo = nombreDelCampo;
      this.valorDelCampo = valorDelCampo;
   }
}
