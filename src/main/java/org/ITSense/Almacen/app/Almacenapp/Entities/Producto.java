package org.ITSense.Almacen.app.Almacenapp.Entities;

import jakarta.persistence.*;
import org.ITSense.Almacen.app.Almacenapp.Enums.EstadoProducto;

@Entity
@Table(name="productos")
public class Producto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private EstadoProducto estado;

    @Enumerated(EnumType.STRING)
    private EstadoProducto tipo;

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

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public EstadoProducto getTipo() {
        return tipo;
    }

    public void setTipo(EstadoProducto tipo) {
        this.tipo = tipo;
    }
}
