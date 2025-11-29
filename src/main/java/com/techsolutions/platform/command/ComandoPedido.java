package com.techsolutions.platform.command;

/**
 * PATRÓN COMMAND
 * Interfaz base para comandos de pedido
 * RF7: Encapsular acciones de pedido como comandos registrables
 */
public interface ComandoPedido {
    void ejecutar();
    void deshacer();
    String obtenerDescripcion();
}
