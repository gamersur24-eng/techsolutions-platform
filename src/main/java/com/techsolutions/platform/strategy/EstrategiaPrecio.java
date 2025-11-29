package com.techsolutions.platform.strategy;

import com.techsolutions.platform.model.Producto;

/**
 * PATRÓN STRATEGY
 * Interfaz para estrategias de cálculo de precios
 * RF9: Soportar distintas estrategias de precios
 */
public interface EstrategiaPrecio {
    double calcularPrecio(Producto producto);
    String obtenerNombreEstrategia();
    String obtenerDescripcion();
}
