package pe.edu.ulima.pw.restaurantes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Plato *--------1 Categoria

@Entity
@Table(name = "platos")
public class PlatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    // 0: Inactivo 1: Activo
    private Integer estado;

    @ManyToOne
    private CategoriaEntity categoria;

    public PlatoEntity() {}

    public Integer getEstado() {
        return estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}