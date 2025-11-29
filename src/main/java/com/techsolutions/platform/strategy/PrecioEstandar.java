package com.techsolutions.platform.strategy;

import com.techsolutions.platform.model.Producto;

/**
 * Estrategia de precio estándar (precio base sin modificaciones)
 */
public class PrecioEstandar implements EstrategiaPrecio {
    
    @Override
    public double calcularPrecio(Producto producto) {
        return producto.getPrecio();
    }
    
    @Override
    public String obtenerNombreEstrategia() {
        return "PRECIO_ESTANDAR";
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Precio base sin modificaciones";
    }
}
