package com.techsolutions.platform.command;

import com.techsolutions.platform.model.Pedido;
import java.time.LocalDateTime;

/**
 * Comando para crear un nuevo pedido
 */
public class ComandoCrearPedido implements ComandoPedido {
    
    private final Pedido pedido;
    private boolean ejecutado = false;
    
    public ComandoCrearPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    @Override
    public void ejecutar() {
        if (!ejecutado) {
            pedido.setEstado("PENDIENTE");
            pedido.setFechaCreacion(LocalDateTime.now());
            pedido.calcularTotal();
            ejecutado = true;
            
            System.out.println("\n✅ Pedido creado exitosamente");
            System.out.println("   ID: " + pedido.getId());
            System.out.println("   Cliente: " + pedido.getClienteId());
            System.out.println("   Total: $" + pedido.getTotal());
            System.out.println("   Estado: " + pedido.getEstado());
        }
    }
    
    @Override
    public void deshacer() {
        if (ejecutado) {
            pedido.setEstado("CANCELADO");
            ejecutado = false;
            System.out.println("\n↩️ Creación de pedido deshecha - ID: " + pedido.getId());
        }
    }
    
    @Override
    public String obtenerDescripcion() {
        return "Crear pedido: " + pedido.getId();
    }
}
