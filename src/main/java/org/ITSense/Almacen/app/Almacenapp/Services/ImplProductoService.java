package org.ITSense.Almacen.app.Almacenapp.Services;

import org.ITSense.Almacen.app.Almacenapp.Entities.Producto;
import org.ITSense.Almacen.app.Almacenapp.Enums.EstadoProducto;
import org.ITSense.Almacen.app.Almacenapp.Repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImplProductoService implements ProductoService{

    @Autowired private ProductoRepository productoRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodosLosProductos() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductosPorEstado(String estado) {
        return productoRepository.findByEstado(estado);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Producto> obtenerProductoPorId(Long id) {
//        return productoRepository.findById(id);
//    }

    @Override
    @Transactional
    public Producto registrarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Producto producto) {
        // Verifica si el producto ya existe
        if (producto.getId() == null || !productoRepository.existsById(producto.getId())) {
            throw new RuntimeException("El producto no existe.");
        }

        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void marcarProductoComoNoDisponible(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            if (EstadoProducto.NO_DISPONIBLE.equals(EstadoProducto.valueOf("NO_DISPONIBLE"))) {
                producto.setEstado(EstadoProducto.NO_DISPONIBLE);
                productoRepository.save(producto);
            } else {
                throw new RuntimeException("Valor de estado no válido.");
            }
        } else {
            throw new RuntimeException("Producto no encontrado.");
        }
    }

    @Override
    @Transactional
    public void marcarProductoComoDefectuoso(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            if (EstadoProducto.DISPONIBLE_DEFECTUOSO.equals(EstadoProducto.valueOf("DISPONIBLE_DEFECTUOSO"))) {
                producto.setEstado(EstadoProducto.DISPONIBLE_DEFECTUOSO);
                productoRepository.save(producto);
            } else {
                throw new RuntimeException("Valor de estado no válido.");
            }
        } else {
            throw new RuntimeException("Producto no encontrado.");
        }
    }

}
