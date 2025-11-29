package com.techsolutions.platform.iterator;

import com.techsolutions.platform.model.Producto;
import java.util.List;

/**
 * Iterador concreto con soporte de paginación
 */
public class IteradorProductosPaginado implements IteradorProductos {
    
    private final List<Producto> productos;
    private int posicion;
    private final int elementosPorPagina;
    
    public IteradorProductosPaginado(List<Producto> productos, int elementosPorPagina) {
        this.productos = productos;
        this.posicion = 0;
        this.elementosPorPagina = elementosPorPagina;
    }
    
    @Override
    public boolean tieneSiguiente() {
        return posicion < productos.size();
    }
    
    @Override
    public Producto siguiente() {
        if (!tieneSiguiente()) {
            return null;
        }
        return productos.get(posicion++);
    }
    
    @Override
    public void reiniciar() {
        posicion = 0;
    }
    
    @Override
    public int posicionActual() {
        return posicion;
    }
    
    @Override
    public int totalElementos() {
        return productos.size();
    }
    
    public int getPaginaActual() {
        return (posicion / elementosPorPagina) + 1;
    }
    
    public int getTotalPaginas() {
        return (int) Math.ceil((double) productos.size() / elementosPorPagina);
    }
    
    public List<Producto> obtenerPagina(int numeroPagina) {
        int inicio = (numeroPagina - 1) * elementosPorPagina;
        int fin = Math.min(inicio + elementosPorPagina, productos.size());
        
        if (inicio >= productos.size() || inicio < 0) {
            return List.of();
        }
        
        return productos.subList(inicio, fin);
    }
}
