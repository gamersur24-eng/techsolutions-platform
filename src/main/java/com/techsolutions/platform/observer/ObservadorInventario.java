package com.techsolutions.platform.observer;

import com.techsolutions.platform.model.Producto;

/**
 * PATRÓN OBSERVER
 * Interfaz para observadores que reciben notificaciones de inventario
 * RF5: Notificar cuando el stock caiga por debajo del mínimo
 */
public interface ObservadorInventario {
    void notificar(Producto producto, String mensaje);
    String obtenerRol();
}
