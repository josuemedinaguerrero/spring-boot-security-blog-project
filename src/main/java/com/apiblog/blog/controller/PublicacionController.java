package com.apiblog.blog.controller;

import com.apiblog.blog.dto.PublicacionDTO;
import com.apiblog.blog.entity.Publicacion;
import com.apiblog.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

   @Autowired
   private PublicacionService publicacionService;

   @PreAuthorize("hasRole('ADMIN')")
   @PostMapping
   public ResponseEntity<Publicacion> guardarPublicacion( @Valid @RequestBody PublicacionDTO publicacionDTO ) {
      return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
   }

   @GetMapping
   public List<Publicacion> listarPublicaciones(
           @RequestParam(value = "pageNo", defaultValue = "0", required = false) int numeroDePagina,
           @RequestParam(value = "pageSize", defaultValue = "10", required = false) int medidaDePagina,
           @RequestParam(value = "sortBy", defaultValue = "id", required = false) String ordernarPor,
           @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir ) {
      return publicacionService.obtenerTodasPublicaciones( numeroDePagina, medidaDePagina, ordernarPor, sortDir );
   }

   @GetMapping("/{id}")
   public ResponseEntity<Publicacion> obtenerPublicacionPorId( @PathVariable(name = "id") Long id ) {
      return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
   }

   @PreAuthorize("hasRole('ADMIN')")
   @PutMapping("/{id}")
   public ResponseEntity<Publicacion> actualizarPublicacion( @Valid @RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") Long id ) {
      Publicacion publicacion = publicacionService.actualizarPublicacion( publicacionDTO, id );
      return new ResponseEntity<>(publicacion, HttpStatus.OK);
   }

   @PreAuthorize("hasRole('ADMIN')")
   @DeleteMapping("/{id}")
   public ResponseEntity<String> eliminarPublicacion( @PathVariable(name = "id") Long id ) {
      publicacionService.eliminarPublicacion( id );
      return new ResponseEntity<>("Publicación eliminada con éxito", HttpStatus.OK);
   }

}
