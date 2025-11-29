package com.techsolutions.platform.adapter;

/**
 * PATRÓN ADAPTER
 * Interfaz común para todas las pasarelas de pago
 * RF1: Integrar múltiples pasarelas mediante un adaptador común
 */
public interface ProcesadorPago {
    boolean procesarPago(double monto, String referencia);
    String obtenerEstadoPago(String referencia);
    boolean cancelarPago(String referencia);
    String obtenerNombrePasarela();
}
