package com.techsolutions.platform.command;

import com.techsolutions.platform.model.Pedido;

/**
 * Comando para aplicar descuento a un pedido
 */
public class ComandoAplicarDescuento implements ComandoPedido {
    
    private final Pedido pedido;
    private final double montoDescuento;
    private double descuentoAnterior;
    private boolean ejecutado = false;
    
    public ComandoAplicarDescuento(Pedido pedido, double montoDescuento) {
        this.pedido = pedido;
        this.montoDescuento = montoDescuento;
    }
    
    @Override
    public void ejecutar() {
        if (!ejecutado) {
            descuentoAnterior = pedido.getDescuento();
            pedido.setDescuento(pedido.getDescuento() + montoDescuento);
            pedido.calcularTotal();
            ejecutado = true;
            
            System.out.println("\n💰 Descuento aplicado");
            System.out.println("   ID Pedido: " + pedido.getId());
            System.out.println("   Descuento anterior: $" + descuentoAnterior);
            System.out.println("   Descuento adicional: $" + montoDescuento);
            System.out.println("   Descuento total: $" + pedido.getDescuento());
            System.out.println("   Nuevo total: $" + pedido.getTotal());
        }
    }
    
    @Override
    public void deshacer() {
        if (ejecutado) {
            pedido.setDescuento(descuentoAnterior);
            pedido.calcularTotal();
            ejecutado = false;
            
            System.out.println("\n↩️ Descuento revertido - ID: " + pedido.getId());
            System.out.println("   Descuento restaurado: $" + descuentoAnterior);
            System.out.println("   Total restaurado: $" + pedido.getTotal());
        }
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Aplicar descuento de $" + montoDescuento + " al pedido: " + pedido.getId();
    }
}
