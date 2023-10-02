package org.ITSense.Almacen.app.Almacenapp.Repositories;

import org.ITSense.Almacen.app.Almacenapp.Entities.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
    List<Producto> findByEstado(String estado);
}
