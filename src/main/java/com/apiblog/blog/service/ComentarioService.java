package com.apiblog.blog.service;

import com.apiblog.blog.dto.ComentarioDTO;
import com.apiblog.blog.entity.Comentario;

import java.util.List;

public interface ComentarioService {

   public Comentario crearComentario( Long publicacionId, ComentarioDTO comentarioDTO );

   public List<ComentarioDTO> obtenerComentariosPorPublicacionId( Long publicacionId );

   public Comentario obtenerComentarioPorId( Long publicacionId, Long comentarioId );

   public Comentario actualizarComentario( Long publicacionId, Long comentarioId, ComentarioDTO comentarioDTO );

   public void eliminarComentario( Long publicacionId, Long comentarioId );

}
