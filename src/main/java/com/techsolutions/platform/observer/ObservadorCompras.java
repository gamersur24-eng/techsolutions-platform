package com.techsolutions.platform.observer;

import com.techsolutions.platform.model.Producto;

/**
 * Observador para usuarios con rol Compras
 * Recibe notificaciones de stock bajo para reposición
 */
public class ObservadorCompras implements ObservadorInventario {
    
    private final String nombreComprador;
    private final String email;
    
    public ObservadorCompras(String nombreComprador, String email) {
        this.nombreComprador = nombreComprador;
        this.email = email;
    }
    
    @Override
    public void notificar(Producto producto, String mensaje) {
        System.out.println("\n📦 NOTIFICACIÓN AL DEPARTAMENTO DE COMPRAS");
        System.out.println("   Destinatario: " + nombreComprador + " (" + email + ")");
        System.out.println("   Producto: " + producto.getNombre());
        System.out.println("   Stock actual: " + producto.getStock());
        System.out.println("   Stock mínimo: " + producto.getStockMinimo());
        System.out.println("   Mensaje: " + mensaje);
        
        int cantidadSugerida = producto.getStockMinimo() * 2 - producto.getStock();
        System.out.println("   📋 Cantidad sugerida de compra: " + cantidadSugerida + " unidades");
        System.out.println("   🚀 Se recomienda iniciar proceso de compra inmediatamente\n");
    }
    
    @Override
    public String obtenerRol() {
        return "COMPRAS";
    }
}
