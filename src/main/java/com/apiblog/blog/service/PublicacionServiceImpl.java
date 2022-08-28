package com.apiblog.blog.service;

import com.apiblog.blog.dto.PublicacionDTO;
import com.apiblog.blog.entity.Publicacion;
import com.apiblog.blog.exceptions.ResourceNotFoundException;
import com.apiblog.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionServiceImpl implements PublicacionService {

   @Autowired
   private PublicacionRepository publicacionRepository;

   @Override
   public Publicacion crearPublicacion( PublicacionDTO publicacionDTO ) {
      Publicacion publicacion = new Publicacion();
      publicacion.setTitulo( publicacionDTO.getTitulo() );
      publicacion.setContenido( publicacionDTO.getContenido() );
      publicacion.setDescripcion( publicacionDTO.getDescripcion() );
      return publicacionRepository.save( publicacion );
   }

   @Override
   public List<Publicacion> obtenerTodasPublicaciones( int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir ) {
      Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
              ? Sort.by(ordenarPor).ascending()
              : Sort.by(ordenarPor).descending();
      Pageable pageable = PageRequest.of( numeroDePagina, medidaDePagina, sort );
      Page<Publicacion> publicaciones = publicacionRepository.findAll( pageable );
      return publicaciones.getContent();
   }

   @Override
   public Publicacion obtenerPublicacionPorId( Long id ) {
      return publicacionRepository.findById( id )
              .orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", id));
   }

   @Override
   public Publicacion actualizarPublicacion( PublicacionDTO publicacionDTO, Long id ) {
      Publicacion publicacion = publicacionRepository.findById( id )
              .orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", id));
      publicacion.setTitulo( publicacionDTO.getTitulo() );
      publicacion.setDescripcion( publicacionDTO.getDescripcion() );
      publicacion.setContenido( publicacionDTO.getContenido() );

      return publicacionRepository.save( publicacion );
   }

   @Override
   public void eliminarPublicacion( Long id ) {
      Publicacion publicacion = publicacionRepository.findById( id )
              .orElseThrow(() -> new ResourceNotFoundException("Publicación", "id", id));
      publicacionRepository.delete(publicacion);
   }

}
