package com.techsolutions.platform.strategy;

import com.techsolutions.platform.model.Producto;
import org.springframework.stereotype.Service;

/**
 * PATRÓN STRATEGY - Context
 * Calculadora de precios que usa estrategias intercambiables
 * RF10: Seleccionar/cambiar estrategia desde configuración
 */
@Service
public class CalculadoraPrecios {
    
    private EstrategiaPrecio estrategiaActual;
    
    public CalculadoraPrecios() {
        // Estrategia por defecto
        this.estrategiaActual = new PrecioEstandar();
    }
    
    public void cambiarEstrategia(EstrategiaPrecio nuevaEstrategia) {
        this.estrategiaActual = nuevaEstrategia;
        
        System.out.println("\n🔄 Estrategia de precios cambiada");
        System.out.println("   Estrategia: " + nuevaEstrategia.obtenerNombreEstrategia());
        System.out.println("   Descripción: " + nuevaEstrategia.obtenerDescripcion());
    }
    
    public double calcularPrecio(Producto producto) {
        double precioCalculado = estrategiaActual.calcularPrecio(producto);
        
        System.out.println("\n💵 Calculando precio");
        System.out.println("   Producto: " + producto.getNombre());
        System.out.println("   Precio base: $" + producto.getPrecio());
        System.out.println("   Estrategia: " + estrategiaActual.obtenerNombreEstrategia());
        System.out.println("   Precio final: $" + precioCalculado);
        
        return precioCalculado;
    }
    
    public EstrategiaPrecio getEstrategiaActual() {
        return estrategiaActual;
    }
    
    public String obtenerDescripcionEstrategiaActual() {
        return estrategiaActual.obtenerNombreEstrategia() + ": " + 
               estrategiaActual.obtenerDescripcion();
    }
}
