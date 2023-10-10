package org.ITSense.Almacen.app.Almacenapp.Controllers;

import io.swagger.annotations.ApiOperation;
import org.ITSense.Almacen.app.Almacenapp.Entities.Producto;
import org.ITSense.Almacen.app.Almacenapp.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    //Inyeccion dependencias por constructor
    private final ProductoService productoService;
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Método para visualizar inventario filtrado por estado

    @GetMapping()
    public ResponseEntity<List<Producto>> visualizarInventario(@RequestParam(name = "estado", required = false) String estado) {
        List<Producto> productos;
        if (estado == null) {
            productos = productoService.obtenerProductosPorEstado("");
        } else {
            productos = productoService.obtenerProductosPorEstado(estado);
        }
        return ResponseEntity.ok(productos);
    }

    // Método para ingresar mercancía (individual o masivamente)
    @PostMapping
    public ResponseEntity<List<Producto>> ingresarMercancia(@RequestBody List<Producto> productos,
                                                            @RequestParam(name = "fechaIngreso", required = false)
                                                            Date fechaIngreso) {
        List<Producto> productosIngresados = productoService.ingresarProductos(productos, fechaIngreso);
        return ResponseEntity.ok(productosIngresados);
    }

    // Método para marcar un producto como defectuoso
    @PostMapping("/{id}/marcar-defectuoso")
    public ResponseEntity<Void> marcarProductoComoDefectuoso(@PathVariable Long id) {
        boolean exito = productoService.marcarProductoComoDefectuoso(id);
        if (exito) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para retirar un producto
    @PostMapping("/{id}/retirar")
    public ResponseEntity<Void> retirarProducto(@PathVariable Long id,
                                                Date fechaRetiro) {
        boolean exito = productoService.retirarProducto(id, fechaRetiro);
        if (exito) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para visualizar salidas
    @GetMapping("/salidas")
    public ResponseEntity<List<Producto>> visualizarSalidas() {
        List<Producto> productosNoDisponibles = productoService.obtenerProductosNoDisponibles();
        return ResponseEntity.ok(productosNoDisponibles);
    }
}
