package com.techsolutions.platform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de Producto para el sistema de inventario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int stockMinimo;
    private String categoria;
    
    public boolean requiereReposicion() {
        return stock < stockMinimo;
    }
}
