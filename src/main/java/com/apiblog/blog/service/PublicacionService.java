package com.apiblog.blog.service;

import com.apiblog.blog.dto.PublicacionDTO;
import com.apiblog.blog.entity.Publicacion;

import java.util.List;

public interface PublicacionService {

   public Publicacion crearPublicacion( PublicacionDTO publicacionDTO );

   public List<Publicacion> obtenerTodasPublicaciones( int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir );

   public Publicacion obtenerPublicacionPorId( Long id );

   public Publicacion actualizarPublicacion( PublicacionDTO publicacionDTO, Long id );

   public void eliminarPublicacion( Long id );

}
