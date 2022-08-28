package com.apiblog.blog.repository;

import com.apiblog.blog.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

   public List<Comentario> findByPublicacionId( Long publicacionId );

}
