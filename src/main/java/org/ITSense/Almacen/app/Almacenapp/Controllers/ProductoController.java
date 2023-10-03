package org.ITSense.Almacen.app.Almacenapp.Controllers;

import org.ITSense.Almacen.app.Almacenapp.Entities.Producto;
import org.ITSense.Almacen.app.Almacenapp.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        return ResponseEntity.ok(productoService.listarTodosLosProductos());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<Producto>> listarPorId(){
//        return ResponseEntity.ok(productoService.obtenerProductoPorId());
//    }
}
