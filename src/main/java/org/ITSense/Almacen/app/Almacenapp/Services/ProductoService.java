package org.ITSense.Almacen.app.Almacenapp.Services;

import org.ITSense.Almacen.app.Almacenapp.Entities.Producto;


import java.util.Date;
import java.util.List;



public interface ProductoService {
    List<Producto> obtenerProductosPorEstado(String estado);
    List<Producto> ingresarProductos(List<Producto> productos,Date fechaIngreso);
    boolean marcarProductoComoDefectuoso(Long id);

    boolean retirarProducto(Long id, Date fechaRetiro);

    List<Producto> obtenerProductosNoDisponibles();
}
