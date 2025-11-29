package com.techsolutions.platform.proxy;

import com.techsolutions.platform.model.ReporteFinanciero;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación real del servicio de reportes financieros
 * Contiene la lógica de negocio real
 */
public class ServicioReportesReal implements ServicioReportes {
    
    private final Map<String, ReporteFinanciero> repositorioReportes;
    
    public ServicioReportesReal() {
        repositorioReportes = new HashMap<>();
        inicializarReportesDemo();
    }
    
    private void inicializarReportesDemo() {
        ReporteFinanciero reporte1 = new ReporteFinanciero();
        reporte1.setId("RPT-001");
        reporte1.setTitulo("Estado Financiero Q3 2024");
        reporte1.setIngresoTotal(500000.00);
        reporte1.setGastoTotal(350000.00);
        reporte1.setUtilidadNeta(150000.00);
        reporte1.setPeriodo("Q3 2024");
        reporte1.setFechaGeneracion(LocalDateTime.now());
        reporte1.setContenidoDetallado("Contenido sensible: Detalles de cuentas, salarios, etc.");
        
        ReporteFinanciero reporte2 = new ReporteFinanciero();
        reporte2.setId("RPT-002");
        reporte2.setTitulo("Balance General 2024");
        reporte2.setIngresoTotal(1200000.00);
        reporte2.setGastoTotal(900000.00);
        reporte2.setUtilidadNeta(300000.00);
        reporte2.setPeriodo("2024");
        reporte2.setFechaGeneracion(LocalDateTime.now());
        reporte2.setContenidoDetallado("Información confidencial financiera completa.");
        
        repositorioReportes.put("RPT-001", reporte1);
        repositorioReportes.put("RPT-002", reporte2);
    }
    
    @Override
    public ReporteFinanciero obtenerReporteCompleto(String reporteId, String usuarioId, String rol) {
        System.out.println("📊 Accediendo a reporte completo: " + reporteId);
        return repositorioReportes.get(reporteId);
    }
    
    @Override
    public String obtenerResumenReporte(String reporteId) {
        ReporteFinanciero reporte = repositorioReportes.get(reporteId);
        if (reporte == null) {
            return "Reporte no encontrado";
        }
        return String.format("Resumen - %s | Periodo: %s | Utilidad: $%.2f", 
            reporte.getTitulo(), reporte.getPeriodo(), reporte.getUtilidadNeta());
    }
}
