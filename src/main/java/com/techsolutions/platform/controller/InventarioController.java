package com.techsolutions.platform.controller;

import com.techsolutions.platform.model.Producto;
import com.techsolutions.platform.observer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller REST para gestión de inventario (Patrón Observer)
 */
@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    
    @Autowired
    private GestorInventario gestorInventario;
    
    @PostMapping("/suscribir/gerente")
    public ResponseEntity<String> suscribirGerente(
            @RequestParam String nombre,
            @RequestParam String email) {
        
        ObservadorGerente observador = new ObservadorGerente(nombre, email);
        gestorInventario.suscribir(observador);
        
        return ResponseEntity.ok("Gerente " + nombre + " suscrito a notificaciones");
    }
    
    @PostMapping("/suscribir/compras")
    public ResponseEntity<String> suscribirCompras(
            @RequestParam String nombre,
            @RequestParam String email) {
        
        ObservadorCompras observador = new ObservadorCompras(nombre, email);
        gestorInventario.suscribir(observador);
        
        return ResponseEntity.ok("Usuario de Compras " + nombre + " suscrito a notificaciones");
    }
    
    @PutMapping("/actualizar-stock")
    public ResponseEntity<Map<String, Object>> actualizarStock(
            @RequestParam String productoId,
            @RequestParam int cantidad) {
        
        gestorInventario.actualizarStock(productoId, cantidad);
        Producto producto = gestorInventario.obtenerProducto(productoId);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("productoId", productoId);
        respuesta.put("nuevoStock", cantidad);
        respuesta.put("requiereReposicion", producto != null && producto.requiereReposicion());
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PutMapping("/configurar-minimo")
    public ResponseEntity<String> configurarStockMinimo(
            @RequestParam String productoId,
            @RequestParam int stockMinimo) {
        
        gestorInventario.configurarStockMinimo(productoId, stockMinimo);
        return ResponseEntity.ok("Stock mínimo configurado para " + productoId);
    }
    
    @GetMapping("/todos")
    public ResponseEntity<Map<String, Producto>> obtenerInventario() {
        return ResponseEntity.ok(gestorInventario.obtenerInventario());
    }
    
    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable String productoId) {
        Producto producto = gestorInventario.obtenerProducto(productoId);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }
}
