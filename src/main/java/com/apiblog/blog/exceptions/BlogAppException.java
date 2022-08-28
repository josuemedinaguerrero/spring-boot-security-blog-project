package com.apiblog.blog.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BlogAppException extends RuntimeException {

   private static final Long serialVersionUID = 1L;
   private HttpStatus estado;
   protected String mensaje;

   public BlogAppException( HttpStatus estado, String mensaje ) {
      super();
      this.estado = estado;
      this.mensaje = mensaje;
   }

   public BlogAppException( HttpStatus estado, String mensaje, String mensaje1 ) {
      super();
      this.estado = estado;
      this.mensaje = mensaje;
      this.mensaje = mensaje1;
   }

}
