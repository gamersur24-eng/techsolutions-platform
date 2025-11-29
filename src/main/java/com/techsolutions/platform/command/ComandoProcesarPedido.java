package com.techsolutions.platform.command;

import com.techsolutions.platform.model.Pedido;

/**
 * Comando para procesar un pedido existente
 */
public class ComandoProcesarPedido implements ComandoPedido {
    
    private final Pedido pedido;
    private String estadoAnterior;
    private boolean ejecutado = false;
    
    public ComandoProcesarPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    @Override
    public void ejecutar() {
        if (!ejecutado) {
            estadoAnterior = pedido.getEstado();
            pedido.setEstado("PROCESADO");
            ejecutado = true;
            
            System.out.println("\n🔄 Pedido procesado");
            System.out.println("   ID: " + pedido.getId());
            System.out.println("   Estado anterior: " + estadoAnterior);
            System.out.println("   Estado actual: PROCESADO");
        }
    }
    
    @Override
    public void deshacer() {
        if (ejecutado) {
            pedido.setEstado(estadoAnterior);
            ejecutado = false;
            System.out.println("\n↩️ Procesamiento deshecho - ID: " + pedido.getId());
            System.out.println("   Estado restaurado: " + estadoAnterior);
        }
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Procesar pedido: " + pedido.getId();
    }
}
