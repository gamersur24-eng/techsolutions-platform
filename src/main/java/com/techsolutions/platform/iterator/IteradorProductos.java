package com.techsolutions.platform.iterator;

import com.techsolutions.platform.model.Producto;

/**
 * PATRÓN ITERATOR
 * Interfaz para iterar sobre colecciones de productos
 * RF11: Recorrer catálogo con soporte para paginación y filtros
 */
public interface IteradorProductos {
    boolean tieneSiguiente();
    Producto siguiente();
    void reiniciar();
    int posicionActual();
    int totalElementos();
}
