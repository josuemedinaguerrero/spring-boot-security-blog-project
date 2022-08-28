package com.apiblog.blog.service;

import com.apiblog.blog.dto.ComentarioDTO;
import com.apiblog.blog.entity.Comentario;
import com.apiblog.blog.entity.Publicacion;
import com.apiblog.blog.exceptions.BlogAppException;
import com.apiblog.blog.exceptions.ResourceNotFoundException;
import com.apiblog.blog.repository.ComentarioRepository;
import com.apiblog.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService {

   @Autowired
   private ComentarioRepository comentarioRepository;
   @Autowired
   private PublicacionRepository publicacionRepository;

   @Override
   public Comentario crearComentario( Long publicacionId, ComentarioDTO comentarioDTO ) {
      Publicacion publicacion = publicacionRepository.findById( publicacionId )
              .orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", publicacionId));

      Comentario comentario = new Comentario();
      comentario.setPublicacion( publicacion );
      comentario.setNombre( comentarioDTO.getNombre() );
      comentario.setCuerpo( comentarioDTO.getCuerpo() );
      comentario.setEmail( comentarioDTO.getEmail() );
      return comentarioRepository.save( comentario );
   }

   @Override
   public List<ComentarioDTO> obtenerComentariosPorPublicacionId( Long publicacionId ) {
      List<Comentario> comentarios = comentarioRepository.findByPublicacionId( publicacionId );
      return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
   }

   @Override
   public Comentario obtenerComentarioPorId( Long publicacionId, Long comentarioId ) {
      Publicacion publicacion = publicacionRepository.findById( publicacionId )
              .orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", publicacionId));
      Comentario comentario = comentarioRepository.findById( comentarioId )
              .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
      if ( !comentario.getPublicacion().getId().equals(publicacion.getId()) ) {
         throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
      }
      return comentario;
   }

   @Override
   public Comentario actualizarComentario( Long publicacionId, Long comentarioId, ComentarioDTO comentarioDTO ) {
      Publicacion publicacion = publicacionRepository.findById( publicacionId )
              .orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", publicacionId));
      Comentario comentario = comentarioRepository.findById( comentarioId )
              .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
      if ( !comentario.getPublicacion().getId().equals(publicacion.getId()) ) {
         throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
      }
      comentario.setNombre( comentarioDTO.getNombre() );
      comentario.setEmail( comentarioDTO.getEmail() );
      comentario.setCuerpo( comentarioDTO.getCuerpo() );
      return comentarioRepository.save( comentario );
   }

   @Override
   public void eliminarComentario( Long publicacionId, Long comentarioId ) {
      Publicacion publicacion = publicacionRepository.findById( publicacionId )
              .orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", publicacionId));
      Comentario comentario = comentarioRepository.findById( comentarioId )
              .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
      if ( !comentario.getPublicacion().getId().equals(publicacion.getId()) ) {
         throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
      }
      comentarioRepository.delete( comentario );
   }

   private ComentarioDTO mapearDTO( Comentario comentario ) {
      ComentarioDTO comentarioDTO = new ComentarioDTO();
      comentarioDTO.setId( comentario.getId() );
      comentarioDTO.setNombre( comentario.getNombre() );
      comentarioDTO.setEmail( comentario.getEmail() );
      comentarioDTO.setCuerpo( comentario.getCuerpo() );
      return comentarioDTO;
   }

}
