package com.techsolutions.platform.strategy;

import com.techsolutions.platform.model.Producto;

/**
 * Estrategia de precio dinámico (ajustado por demanda o temporada)
 */
public class PrecioDinamico implements EstrategiaPrecio {
    
    private final double factorAjuste;
    private final String razon;
    
    /**
     * @param factorAjuste Factor multiplicador (1.2 = +20%, 0.8 = -20%)
     * @param razon Razón del ajuste (ej: "Alta demanda", "Temporada baja")
     */
    public PrecioDinamico(double factorAjuste, String razon) {
        this.factorAjuste = factorAjuste;
        this.razon = razon;
    }
    
    @Override
    public double calcularPrecio(Producto producto) {
        return producto.getPrecio() * factorAjuste;
    }
    
    @Override
    public String obtenerNombreEstrategia() {
        return "PRECIO_DINAMICO";
    }
    
    @Override
    public String obtenerDescripcion() {
        double porcentaje = (factorAjuste - 1) * 100;
        String signo = porcentaje >= 0 ? "+" : "";
        return String.format("Ajuste dinámico del %s%.0f%% - %s", 
            signo, porcentaje, razon);
    }
    
    public double getFactorAjuste() {
        return factorAjuste;
    }
    
    public String getRazon() {
        return razon;
    }
}
