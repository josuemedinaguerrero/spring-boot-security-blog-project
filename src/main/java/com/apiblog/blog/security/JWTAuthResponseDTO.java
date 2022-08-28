package com.apiblog.blog.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JWTAuthResponseDTO {

   private String tokenDeAcceso;
   private String tipoDeToken = "Bearer";

   public JWTAuthResponseDTO( String tokenDeAcceso ) {
      super();
      this.tokenDeAcceso = tokenDeAcceso;
   }

}
