package com.techsolutions.platform.proxy;

import com.techsolutions.platform.model.ReporteFinanciero;

/**
 * PATRÓN PROXY
 * Interfaz para acceso a reportes financieros
 * RF3: Proteger acceso a reportes mediante proxy con validación
 */
public interface ServicioReportes {
    ReporteFinanciero obtenerReporteCompleto(String reporteId, String usuarioId, String rol);
    String obtenerResumenReporte(String reporteId);
}
