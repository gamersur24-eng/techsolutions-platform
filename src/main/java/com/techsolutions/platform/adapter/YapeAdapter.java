package com.techsolutions.platform.adapter;

/**
 * Adaptador para la pasarela de pago Yape (Perú)
 * Simula la integración con API de Yape
 */
public class YapeAdapter implements ProcesadorPago {
    
    @Override
    public boolean procesarPago(double monto, String referencia) {
        // Simulación de procesamiento con Yape
        System.out.println("📱 Procesando pago con Yape");
        System.out.println("   Monto: S/." + monto);
        System.out.println("   Referencia: " + referencia);
        
        // Simular validación y procesamiento
        if (monto > 0) {
            System.out.println("   ✅ Pago procesado exitosamente con Yape");
            return true;
        }
        System.out.println("   ❌ Error al procesar pago con Yape");
        return false;
    }
    
    @Override
    public String obtenerEstadoPago(String referencia) {
        return "COMPLETADO - Yape ID: YPE-" + referencia;
    }
    
    @Override
    public boolean cancelarPago(String referencia) {
        System.out.println("🔄 Cancelando pago Yape: " + referencia);
        return true;
    }
    
    @Override
    public String obtenerNombrePasarela() {
        return "Yape";
    }
}
