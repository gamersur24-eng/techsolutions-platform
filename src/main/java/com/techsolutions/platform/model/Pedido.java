package com.techsolutions.platform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de Pedido para el sistema de ventas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private String id;
    private String clienteId;
    private List<ItemPedido> items = new ArrayList<>();
    private double subtotal;
    private double descuento;
    private double total;
    private String estado; // PENDIENTE, PROCESADO, CANCELADO
    private LocalDateTime fechaCreacion;
    private String metodoPago;
    
    public void calcularTotal() {
        this.subtotal = items.stream()
            .mapToDouble(item -> item.getPrecio() * item.getCantidad())
            .sum();
        this.total = subtotal - descuento;
    }
}
