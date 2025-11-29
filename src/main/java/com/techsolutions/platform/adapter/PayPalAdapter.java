package com.techsolutions.platform.adapter;

/**
 * Adaptador para la pasarela de pago PayPal
 * Simula la integración con API de PayPal
 */
public class PayPalAdapter implements ProcesadorPago {
    
    @Override
    public boolean procesarPago(double monto, String referencia) {
        // Simulación de procesamiento con PayPal
        System.out.println("💳 Procesando pago con PayPal");
        System.out.println("   Monto: $" + monto);
        System.out.println("   Referencia: " + referencia);
        
        // Simular validación y procesamiento
        if (monto > 0) {
            System.out.println("   ✅ Pago procesado exitosamente con PayPal");
            return true;
        }
        System.out.println("   ❌ Error al procesar pago con PayPal");
        return false;
    }
    
    @Override
    public String obtenerEstadoPago(String referencia) {
        return "COMPLETADO - PayPal ID: PP-" + referencia;
    }
    
    @Override
    public boolean cancelarPago(String referencia) {
        System.out.println("🔄 Cancelando pago PayPal: " + referencia);
        return true;
    }
    
    @Override
    public String obtenerNombrePasarela() {
        return "PayPal";
    }
}
