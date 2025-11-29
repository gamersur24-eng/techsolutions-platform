package com.techsolutions.platform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Item individual dentro de un pedido
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    private String productoId;
    private String nombreProducto;
    private int cantidad;
    private double precio;
}
