package com.techsolutions.platform.strategy;

import com.techsolutions.platform.model.Producto;

/**
 * Estrategia de precio con descuento porcentual
 */
public class PrecioConDescuento implements EstrategiaPrecio {
    
    private final double porcentajeDescuento;
    
    public PrecioConDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
    
    @Override
    public double calcularPrecio(Producto producto) {
        double precioBase = producto.getPrecio();
        double descuento = precioBase * (porcentajeDescuento / 100);
        return precioBase - descuento;
    }
    
    @Override
    public String obtenerNombreEstrategia() {
        return "PRECIO_CON_DESCUENTO";
    }
    
    @Override
    public String obtenerDescripcion() {
        return String.format("Descuento del %.0f%% sobre precio base", porcentajeDescuento);
    }
    
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}
