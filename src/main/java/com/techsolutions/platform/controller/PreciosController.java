package com.techsolutions.platform.controller;

import com.techsolutions.platform.model.Producto;
import com.techsolutions.platform.strategy.*;
import com.techsolutions.platform.observer.GestorInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller REST para estrategias de precios (Patrón Strategy)
 */
@RestController
@RequestMapping("/api/precios")
public class PreciosController {
    
    @Autowired
    private CalculadoraPrecios calculadora;
    
    @Autowired
    private GestorInventario gestorInventario;
    
    @PostMapping("/estrategia/estandar")
    public ResponseEntity<Map<String, String>> cambiarAEstandar() {
        calculadora.cambiarEstrategia(new PrecioEstandar());
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("estrategia", "PRECIO_ESTANDAR");
        respuesta.put("descripcion", calculadora.obtenerDescripcionEstrategiaActual());
        respuesta.put("mensaje", "Estrategia cambiada a precio estándar");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/estrategia/descuento")
    public ResponseEntity<Map<String, String>> cambiarADescuento(
            @RequestParam double porcentaje) {
        
        calculadora.cambiarEstrategia(new PrecioConDescuento(porcentaje));
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("estrategia", "PRECIO_CON_DESCUENTO");
        respuesta.put("porcentaje", porcentaje + "%");
        respuesta.put("descripcion", calculadora.obtenerDescripcionEstrategiaActual());
        respuesta.put("mensaje", "Estrategia cambiada a descuento del " + porcentaje + "%");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/estrategia/dinamico")
    public ResponseEntity<Map<String, String>> cambiarADinamico(
            @RequestParam double factor,
            @RequestParam String razon) {
        
        calculadora.cambiarEstrategia(new PrecioDinamico(factor, razon));
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("estrategia", "PRECIO_DINAMICO");
        respuesta.put("factor", String.valueOf(factor));
        respuesta.put("razon", razon);
        respuesta.put("descripcion", calculadora.obtenerDescripcionEstrategiaActual());
        respuesta.put("mensaje", "Estrategia cambiada a precio dinámico");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/calcular/{productoId}")
    public ResponseEntity<Map<String, Object>> calcularPrecio(@PathVariable String productoId) {
        Producto producto = gestorInventario.obtenerProducto(productoId);
        
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        
        double precioCalculado = calculadora.calcularPrecio(producto);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("productoId", productoId);
        respuesta.put("nombreProducto", producto.getNombre());
        respuesta.put("precioBase", producto.getPrecio());
        respuesta.put("precioCalculado", precioCalculado);
        respuesta.put("estrategiaActual", calculadora.getEstrategiaActual().obtenerNombreEstrategia());
        respuesta.put("descripcionEstrategia", calculadora.getEstrategiaActual().obtenerDescripcion());
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/estrategia/actual")
    public ResponseEntity<Map<String, String>> obtenerEstrategiaActual() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("estrategia", calculadora.getEstrategiaActual().obtenerNombreEstrategia());
        respuesta.put("descripcion", calculadora.getEstrategiaActual().obtenerDescripcion());
        
        return ResponseEntity.ok(respuesta);
    }
}
