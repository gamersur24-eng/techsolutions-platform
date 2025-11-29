package com.techsolutions.platform.proxy;

import com.techsolutions.platform.model.ReporteFinanciero;
import org.springframework.stereotype.Service;

/**
 * PATRÓN PROXY
 * Proxy que controla el acceso a reportes financieros
 * RF4: Solo Gerente o Contador pueden acceder a reportes completos
 */
@Service
public class ProxyServicioReportes implements ServicioReportes {
    
    private final ServicioReportes servicioReal;
    
    public ProxyServicioReportes() {
        this.servicioReal = new ServicioReportesReal();
    }
    
    @Override
    public ReporteFinanciero obtenerReporteCompleto(String reporteId, String usuarioId, String rol) {
        System.out.println("\n🔒 PROXY: Validando acceso a reporte financiero");
        System.out.println("   Usuario: " + usuarioId);
        System.out.println("   Rol: " + rol);
        
        // Validar rol (RF4)
        if (!tienePermiso(rol)) {
            System.out.println("   ❌ ACCESO DENEGADO - Rol no autorizado");
            System.out.println("   ℹ️ Solo usuarios con rol GERENTE o CONTADOR pueden acceder\n");
            
            ReporteFinanciero reporteRestringido = new ReporteFinanciero();
            reporteRestringido.setId(reporteId);
            reporteRestringido.setTitulo("ACCESO DENEGADO");
            reporteRestringido.setContenidoDetallado("No tiene permisos para ver este reporte");
            return reporteRestringido;
        }
        
        System.out.println("   ✅ ACCESO AUTORIZADO - Obteniendo reporte completo\n");
        
        // Registrar auditoría
        registrarAcceso(usuarioId, reporteId, rol);
        
        // Delegar al servicio real
        return servicioReal.obtenerReporteCompleto(reporteId, usuarioId, rol);
    }
    
    @Override
    public String obtenerResumenReporte(String reporteId) {
        // El resumen es público, no requiere validación
        return servicioReal.obtenerResumenReporte(reporteId);
    }
    
    private boolean tienePermiso(String rol) {
        return "GERENTE".equalsIgnoreCase(rol) || "CONTADOR".equalsIgnoreCase(rol);
    }
    
    private void registrarAcceso(String usuarioId, String reporteId, String rol) {
        System.out.println("📝 Auditoría: Usuario " + usuarioId + " (" + rol + ") accedió a " + reporteId);
    }
}
