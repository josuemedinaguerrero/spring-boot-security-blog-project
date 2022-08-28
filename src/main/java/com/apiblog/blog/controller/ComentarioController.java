package com.apiblog.blog.controller;

import com.apiblog.blog.dto.ComentarioDTO;
import com.apiblog.blog.entity.Comentario;
import com.apiblog.blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentarioController {

   @Autowired
   private ComentarioService comentarioService;

   @GetMapping("/publicaciones/{publicacionId}/comentarios")
   public List<ComentarioDTO> listarComentariosPorPublicacionId( @PathVariable(name = "publicacionId") Long publicacionId ) {
      return comentarioService.obtenerComentariosPorPublicacionId( publicacionId );
   }

   @GetMapping("/publicaciones/{publicacionId}/comentarios/{id}")
   public ResponseEntity<Comentario> obtenerComentarioPorId(
           @PathVariable(name = "publicacionId") Long publicacionId,
           @PathVariable(name = "id") Long comentarioId ) {
      Comentario comentario = comentarioService.obtenerComentarioPorId( publicacionId, comentarioId );
      return new ResponseEntity<>(comentario, HttpStatus.OK);
   }

   @PostMapping("/publicaciones/{publicacionId}/comentarios")
   public ResponseEntity<Comentario> guardarComentario(
           @Valid @RequestBody ComentarioDTO comentarioDTO,
           @PathVariable(name = "publicacionId") Long publicacionId ) {
      return new ResponseEntity<>(comentarioService.crearComentario( publicacionId, comentarioDTO ), HttpStatus.CREATED);
   }

   @PutMapping("/publicaciones/{publicacionId}/comentarios/{id}")
   public ResponseEntity<Comentario> actualizarComentario(
           @Valid @RequestBody ComentarioDTO comentarioDTO,
           @PathVariable(name = "publicacionId") Long publicacionId,
           @PathVariable(name = "id") Long comentarioId ) {
      Comentario comentario = comentarioService.actualizarComentario( publicacionId, comentarioId, comentarioDTO );
      return new ResponseEntity<>(comentario, HttpStatus.OK);
   }

   @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{id}")
   public ResponseEntity<String> eliminarComentario(
           @PathVariable(name = "publicacionId") Long publicacionId,
           @PathVariable(name = "id") Long comentarioId ) {
      comentarioService.eliminarComentario( publicacionId, comentarioId );
      return new ResponseEntity<>("Comentario eliminado con éxito", HttpStatus.OK);
   }

}
