package org.ITSense.Almacen.app.Almacenapp.Services;

import org.ITSense.Almacen.app.Almacenapp.Entities.Producto;
import org.ITSense.Almacen.app.Almacenapp.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductoService {


    List<Producto> listarTodosLosProductos();
    List<Producto> listarProductosPorEstado(String estado);
    Optional<Producto> obtenerProductoPorId(Long id);
    Producto registrarProducto(Producto producto);
    Producto actualizarProducto(Producto producto);
    void marcarProductoComoNoDisponible(Long id);
    void marcarProductoComoDefectuoso(Long id);
}
