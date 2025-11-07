package com.example.Level_up.controller;
import com.example.Level_up.model.Producto;
import com.example.Level_up.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Producto Management System")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "View a list of available products")
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by id")
    public Producto getProductoById(@PathVariable Long id){
        return productoService.getProductoById(id);
    }

    @PostMapping
    @Operation(summary = "Add a new producto")
    public Producto createProducto(@RequestBody Producto producto){
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing producto")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto existingProducto = productoService.getProductoById(id);
        if (existingProducto != null){
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setCategoria(producto.getCategoria());
            existingProducto.setPrecio(producto.getPrecio());
            return productoService.saveProducto(existingProducto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a producto")
    public void deleteProducto(@PathVariable Long id){
        productoService.deleteProducto(id);
    }
}
