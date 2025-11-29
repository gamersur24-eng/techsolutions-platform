package com.techsolutions.platform.command;

import com.techsolutions.platform.model.Pedido;

/**
 * Comando para cancelar un pedido
 */
public class ComandoCancelarPedido implements ComandoPedido {
    
    private final Pedido pedido;
    private String estadoAnterior;
    private boolean ejecutado = false;
    
    public ComandoCancelarPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    @Override
    public void ejecutar() {
        if (!ejecutado) {
            estadoAnterior = pedido.getEstado();
            pedido.setEstado("CANCELADO");
            ejecutado = true;
            
            System.out.println("\n❌ Pedido cancelado");
            System.out.println("   ID: " + pedido.getId());
            System.out.println("   Estado anterior: " + estadoAnterior);
            System.out.println("   Estado actual: CANCELADO");
        }
    }
    
    @Override
    public void deshacer() {
        if (ejecutado) {
            pedido.setEstado(estadoAnterior);
            ejecutado = false;
            System.out.println("\n↩️ Cancelación deshecha - ID: " + pedido.getId());
            System.out.println("   Estado restaurado: " + estadoAnterior);
        }
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Cancelar pedido: " + pedido.getId();
    }
}
