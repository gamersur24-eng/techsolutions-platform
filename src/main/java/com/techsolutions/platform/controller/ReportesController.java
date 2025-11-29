package com.techsolutions.platform.controller;

import com.techsolutions.platform.model.ReporteFinanciero;
import com.techsolutions.platform.proxy.ProxyServicioReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST para acceso a reportes financieros (Patrón Proxy)
 */
@RestController
@RequestMapping("/api/reportes")
public class ReportesController {
    
    @Autowired
    private ProxyServicioReportes servicioReportes;
    
    @GetMapping("/completo/{reporteId}")
    public ResponseEntity<ReporteFinanciero> obtenerReporteCompleto(
            @PathVariable String reporteId,
            @RequestParam String usuarioId,
            @RequestParam String rol) {
        
        ReporteFinanciero reporte = servicioReportes.obtenerReporteCompleto(
            reporteId, usuarioId, rol
        );
        
        return ResponseEntity.ok(reporte);
    }
    
    @GetMapping("/resumen/{reporteId}")
    public ResponseEntity<String> obtenerResumen(@PathVariable String reporteId) {
        String resumen = servicioReportes.obtenerResumenReporte(reporteId);
        return ResponseEntity.ok(resumen);
    }
}
