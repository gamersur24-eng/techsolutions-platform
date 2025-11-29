package com.techsolutions.platform.adapter;

/**
 * Adaptador para la pasarela de pago Plin (Perú)
 * Simula la integración con API de Plin
 */
public class PlinAdapter implements ProcesadorPago {
    
    @Override
    public boolean procesarPago(double monto, String referencia) {
        // Simulación de procesamiento con Plin
        System.out.println("💰 Procesando pago con Plin");
        System.out.println("   Monto: S/." + monto);
        System.out.println("   Referencia: " + referencia);
        
        // Simular validación y procesamiento
        if (monto > 0) {
            System.out.println("   ✅ Pago procesado exitosamente con Plin");
            return true;
        }
        System.out.println("   ❌ Error al procesar pago con Plin");
        return false;
    }
    
    @Override
    public String obtenerEstadoPago(String referencia) {
        return "COMPLETADO - Plin ID: PLN-" + referencia;
    }
    
    @Override
    public boolean cancelarPago(String referencia) {
        System.out.println("🔄 Cancelando pago Plin: " + referencia);
        return true;
    }
    
    @Override
    public String obtenerNombrePasarela() {
        return "Plin";
    }
}
