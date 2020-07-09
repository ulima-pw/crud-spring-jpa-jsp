package pe.edu.ulima.pw.restaurantes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.ulima.pw.restaurantes.entities.PlatoEntity;

public interface PlatoRepository extends JpaRepository<PlatoEntity, Long>{
    
}