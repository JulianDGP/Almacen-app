package org.ITSense.Almacen.app.Almacenapp.Services;

import org.ITSense.Almacen.app.Almacenapp.Entities.Producto;
import org.ITSense.Almacen.app.Almacenapp.Enums.EstadoProducto;
import org.ITSense.Almacen.app.Almacenapp.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImplProductoService implements ProductoService {

    private final ProductoRepository productoRepository;
    //Inyecion de dependencias por constructor
    @Autowired
    public ImplProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerProductosPorEstado(String estado) {
        return switch (estado) {
            case "DISPONIBLE" -> productoRepository.findByEstado(EstadoProducto.DISPONIBLE);
            case "DISPONIBLE_DEFECTUOSO" -> productoRepository.findByEstado(EstadoProducto.DISPONIBLE_DEFECTUOSO);
            default ->
                // Si el estado no coincide con ninguno de los casos anteriores, devuelve todos los productos.
             productoRepository.findByEstadoNot(EstadoProducto.NO_DISPONIBLE);
        };
    }

    @Override
    public List<Producto> ingresarProductos(List<Producto> productos, Date fechaIngreso) {
        if (fechaIngreso == null || fechaIngreso.after(new Date())) {
            Date fechaActual = new Date();
            productos.forEach(producto -> producto.setFechaIngreso(fechaActual));
        }else{
            productos.forEach(producto -> producto.setFechaIngreso(fechaIngreso));
        }
        return (List<Producto>) productoRepository.saveAll(productos);
    }

    @Override
    public boolean marcarProductoComoDefectuoso(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setEstado(EstadoProducto.DISPONIBLE_DEFECTUOSO);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }


    @Override
    public boolean retirarProducto(Long id, Date fechaRetiro) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setEstado(EstadoProducto.NO_DISPONIBLE);

            if (fechaRetiro == null || fechaRetiro.after(new Date())) {
                Date fechaActual = new Date();
                producto.setFechaRetiro(fechaActual);
            }else{
                producto.setFechaRetiro(fechaRetiro);
            }
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    @Override
    public List<Producto> obtenerProductosNoDisponibles() {
        return productoRepository.findByEstado(EstadoProducto.NO_DISPONIBLE);

    }
}

