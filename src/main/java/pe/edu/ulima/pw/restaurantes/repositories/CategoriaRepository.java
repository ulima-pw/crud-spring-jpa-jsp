package pe.edu.ulima.pw.restaurantes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.ulima.pw.restaurantes.entities.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{
    
}