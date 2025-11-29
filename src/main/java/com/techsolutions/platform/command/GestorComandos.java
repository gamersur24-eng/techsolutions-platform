package com.techsolutions.platform.command;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * PATRÓN COMMAND - Invoker
 * Gestor que ejecuta comandos y mantiene historial
 * RF7: Registrar acciones en historial
 */
@Service
public class GestorComandos {
    
    private final List<ComandoPedido> historial;
    private int posicionActual;
    
    public GestorComandos() {
        this.historial = new ArrayList<>();
        this.posicionActual = -1;
    }
    
    public void ejecutarComando(ComandoPedido comando) {
        // Eliminar comandos adelante si estamos en medio del historial
        if (posicionActual < historial.size() - 1) {
            historial.subList(posicionActual + 1, historial.size()).clear();
        }
        
        comando.ejecutar();
        historial.add(comando);
        posicionActual++;
        
        System.out.println("📝 Comando registrado en historial [" + (posicionActual + 1) + 
            "/" + historial.size() + "]: " + comando.obtenerDescripcion());
    }
    
    public boolean deshacer() {
        if (posicionActual >= 0) {
            ComandoPedido comando = historial.get(posicionActual);
            comando.deshacer();
            posicionActual--;
            
            System.out.println("⬅️ Comando deshecho. Posición actual: " + 
                (posicionActual + 1) + "/" + historial.size());
            return true;
        }
        
        System.out.println("⚠️ No hay comandos para deshacer");
        return false;
    }
    
    public boolean rehacer() {
        if (posicionActual < historial.size() - 1) {
            posicionActual++;
            ComandoPedido comando = historial.get(posicionActual);
            comando.ejecutar();
            
            System.out.println("➡️ Comando rehecho. Posición actual: " + 
                (posicionActual + 1) + "/" + historial.size());
            return true;
        }
        
        System.out.println("⚠️ No hay comandos para rehacer");
        return false;
    }
    
    public List<String> obtenerHistorial() {
        List<String> descripciones = new ArrayList<>();
        for (int i = 0; i < historial.size(); i++) {
            String prefijo = (i == posicionActual) ? "→ " : "  ";
            descripciones.add(prefijo + (i + 1) + ". " + historial.get(i).obtenerDescripcion());
        }
        return descripciones;
    }
    
    public void limpiarHistorial() {
        historial.clear();
        posicionActual = -1;
        System.out.println("🗑️ Historial limpiado");
    }
}
