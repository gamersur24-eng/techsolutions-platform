package com.techsolutions.platform.memento;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PATRÓN MEMENTO - Caretaker
 * Gestor de mementos que mantiene historial de estados
 */
@Service
public class GestorMementos {
    
    private final Map<String, List<MementoPedido>> historialPorPedido;
    
    public GestorMementos() {
        this.historialPorPedido = new HashMap<>();
    }
    
    public void guardarMemento(String pedidoId, MementoPedido memento) {
        historialPorPedido.computeIfAbsent(pedidoId, k -> new ArrayList<>()).add(memento);
        
        System.out.println("\n💾 Memento guardado para pedido: " + pedidoId);
        System.out.println("   " + memento.obtenerResumen());
        System.out.println("   Total de mementos para este pedido: " + 
            historialPorPedido.get(pedidoId).size());
    }
    
    public MementoPedido obtenerUltimoMemento(String pedidoId) {
        List<MementoPedido> mementos = historialPorPedido.get(pedidoId);
        
        if (mementos == null || mementos.isEmpty()) {
            System.out.println("⚠️ No hay mementos guardados para el pedido: " + pedidoId);
            return null;
        }
        
        return mementos.get(mementos.size() - 1);
    }
    
    public MementoPedido obtenerMemento(String pedidoId, int indice) {
        List<MementoPedido> mementos = historialPorPedido.get(pedidoId);
        
        if (mementos == null || indice < 0 || indice >= mementos.size()) {
            System.out.println("⚠️ Memento no encontrado en el índice: " + indice);
            return null;
        }
        
        return mementos.get(indice);
    }
    
    public List<String> listarMementos(String pedidoId) {
        List<MementoPedido> mementos = historialPorPedido.get(pedidoId);
        List<String> resumenes = new ArrayList<>();
        
        if (mementos == null || mementos.isEmpty()) {
            resumenes.add("No hay mementos guardados para este pedido");
            return resumenes;
        }
        
        for (int i = 0; i < mementos.size(); i++) {
            resumenes.add((i + 1) + ". " + mementos.get(i).obtenerResumen());
        }
        
        return resumenes;
    }
    
    public void limpiarMementos(String pedidoId) {
        historialPorPedido.remove(pedidoId);
        System.out.println("🗑️ Mementos eliminados para pedido: " + pedidoId);
    }
}
