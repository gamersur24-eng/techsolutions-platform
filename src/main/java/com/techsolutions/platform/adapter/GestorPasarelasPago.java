package com.techsolutions.platform.adapter;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * Servicio que gestiona las pasarelas de pago
 * RF2: Habilitar/deshabilitar pasarelas desde panel de configuración
 */
@Service
public class GestorPasarelasPago {
    
    private final Map<String, ProcesadorPago> pasarelas;
    private final Map<String, Boolean> estadoPasarelas;
    
    public GestorPasarelasPago() {
        pasarelas = new HashMap<>();
        estadoPasarelas = new HashMap<>();
        
        // Inicializar pasarelas
        pasarelas.put("PAYPAL", new PayPalAdapter());
        pasarelas.put("YAPE", new YapeAdapter());
        pasarelas.put("PLIN", new PlinAdapter());
        
        // Todas habilitadas por defecto
        estadoPasarelas.put("PAYPAL", true);
        estadoPasarelas.put("YAPE", true);
        estadoPasarelas.put("PLIN", true);
    }
    
    public boolean procesarPago(String tipoPasarela, double monto, String referencia) {
        if (!estadoPasarelas.getOrDefault(tipoPasarela, false)) {
            System.out.println("⚠️ La pasarela " + tipoPasarela + " está deshabilitada");
            return false;
        }
        
        ProcesadorPago procesador = pasarelas.get(tipoPasarela);
        if (procesador == null) {
            System.out.println("❌ Pasarela no encontrada: " + tipoPasarela);
            return false;
        }
        
        return procesador.procesarPago(monto, referencia);
    }
    
    public void habilitarPasarela(String tipoPasarela) {
        if (pasarelas.containsKey(tipoPasarela)) {
            estadoPasarelas.put(tipoPasarela, true);
            System.out.println("✅ Pasarela " + tipoPasarela + " habilitada");
        }
    }
    
    public void deshabilitarPasarela(String tipoPasarela) {
        if (pasarelas.containsKey(tipoPasarela)) {
            estadoPasarelas.put(tipoPasarela, false);
            System.out.println("⛔ Pasarela " + tipoPasarela + " deshabilitada");
        }
    }
    
    public Map<String, Boolean> obtenerEstadoPasarelas() {
        return new HashMap<>(estadoPasarelas);
    }
}
