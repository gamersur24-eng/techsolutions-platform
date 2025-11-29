package com.techsolutions.platform.memento;

import com.techsolutions.platform.model.ItemPedido;
import com.techsolutions.platform.model.Pedido;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PATRÓN MEMENTO
 * Memento que guarda el estado completo de un pedido
 * RF8: Permitir revertir pedido a estado anterior
 */
public class MementoPedido {
    
    private final String id;
    private final String clienteId;
    private final List<ItemPedido> items;
    private final double subtotal;
    private final double descuento;
    private final double total;
    private final String estado;
    private final LocalDateTime fechaCreacion;
    private final String metodoPago;
    private final LocalDateTime fechaGuardado;
    
    public MementoPedido(Pedido pedido) {
        this.id = pedido.getId();
        this.clienteId = pedido.getClienteId();
        this.items = new ArrayList<>(pedido.getItems());
        this.subtotal = pedido.getSubtotal();
        this.descuento = pedido.getDescuento();
        this.total = pedido.getTotal();
        this.estado = pedido.getEstado();
        this.fechaCreacion = pedido.getFechaCreacion();
        this.metodoPago = pedido.getMetodoPago();
        this.fechaGuardado = LocalDateTime.now();
    }
    
    public Pedido restaurarPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(this.id);
        pedido.setClienteId(this.clienteId);
        pedido.setItems(new ArrayList<>(this.items));
        pedido.setSubtotal(this.subtotal);
        pedido.setDescuento(this.descuento);
        pedido.setTotal(this.total);
        pedido.setEstado(this.estado);
        pedido.setFechaCreacion(this.fechaCreacion);
        pedido.setMetodoPago(this.metodoPago);
        return pedido;
    }
    
    public String obtenerResumen() {
        return String.format("Memento de %s | Estado: %s | Total: $%.2f | Guardado: %s",
            id, estado, total, fechaGuardado);
    }
    
    public LocalDateTime getFechaGuardado() {
        return fechaGuardado;
    }
}
