package com.techsolutions.platform.controller;

import com.techsolutions.platform.iterator.*;
import com.techsolutions.platform.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller REST para catálogo de productos (Patrón Iterator)
 */
@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {
    
    @Autowired
    private CatalogoProductos catalogo;
    
    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listarProductos(
            @RequestParam(defaultValue = "5") int elementosPorPagina) {
        
        IteradorProductos iterador = catalogo.crearIterador(elementosPorPagina);
        List<Producto> productos = new ArrayList<>();
        
        while (iterador.tieneSiguiente()) {
            productos.add(iterador.siguiente());
        }
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("productos", productos);
        respuesta.put("total", iterador.totalElementos());
        respuesta.put("elementosPorPagina", elementosPorPagina);
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/pagina/{numeroPagina}")
    public ResponseEntity<Map<String, Object>> obtenerPagina(
            @PathVariable int numeroPagina,
            @RequestParam(defaultValue = "5") int elementosPorPagina) {
        
        IteradorProductosPaginado iterador = (IteradorProductosPaginado) 
            catalogo.crearIterador(elementosPorPagina);
        
        List<Producto> productos = iterador.obtenerPagina(numeroPagina);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("productos", productos);
        respuesta.put("paginaActual", numeroPagina);
        respuesta.put("totalPaginas", iterador.getTotalPaginas());
        respuesta.put("elementosPorPagina", elementosPorPagina);
        respuesta.put("totalProductos", iterador.totalElementos());
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/filtrar/categoria/{categoria}")
    public ResponseEntity<Map<String, Object>> filtrarPorCategoria(
            @PathVariable String categoria,
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "5") int elementosPorPagina) {
        
        IteradorProductosPaginado iterador = (IteradorProductosPaginado) 
            catalogo.crearIteradorFiltrado(categoria, elementosPorPagina);
        
        List<Producto> productos = iterador.obtenerPagina(pagina);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("productos", productos);
        respuesta.put("categoria", categoria);
        respuesta.put("paginaActual", pagina);
        respuesta.put("totalPaginas", iterador.getTotalPaginas());
        respuesta.put("totalProductos", iterador.totalElementos());
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<Map<String, Object>> buscarProductos(
            @RequestParam String termino,
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "5") int elementosPorPagina) {
        
        IteradorProductosPaginado iterador = (IteradorProductosPaginado) 
            catalogo.crearIteradorBusqueda(termino, elementosPorPagina);
        
        List<Producto> productos = iterador.obtenerPagina(pagina);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("productos", productos);
        respuesta.put("termino", termino);
        respuesta.put("paginaActual", pagina);
        respuesta.put("totalPaginas", iterador.getTotalPaginas());
        respuesta.put("totalProductos", iterador.totalElementos());
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/categorias")
    public ResponseEntity<List<String>> obtenerCategorias() {
        return ResponseEntity.ok(catalogo.obtenerCategorias());
    }
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalProductos", catalogo.getTotalProductos());
        stats.put("categorias", catalogo.obtenerCategorias());
        stats.put("totalCategorias", catalogo.obtenerCategorias().size());
        
        return ResponseEntity.ok(stats);
    }
}
