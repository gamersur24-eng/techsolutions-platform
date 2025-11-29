package com.techsolutions.platform.observer;

import com.techsolutions.platform.model.Producto;

/**
 * Observador para usuarios con rol Gerente
 * Recibe notificaciones de stock bajo
 */
public class ObservadorGerente implements ObservadorInventario {
    
    private final String nombreGerente;
    private final String email;
    
    public ObservadorGerente(String nombreGerente, String email) {
        this.nombreGerente = nombreGerente;
        this.email = email;
    }
    
    @Override
    public void notificar(Producto producto, String mensaje) {
        System.out.println("\n📧 NOTIFICACIÓN AL GERENTE");
        System.out.println("   Destinatario: " + nombreGerente + " (" + email + ")");
        System.out.println("   Producto: " + producto.getNombre());
        System.out.println("   Stock actual: " + producto.getStock());
        System.out.println("   Stock mínimo: " + producto.getStockMinimo());
        System.out.println("   Mensaje: " + mensaje);
        System.out.println("   ⚠️ Se requiere aprobación para compra urgente\n");
    }
    
    @Override
    public String obtenerRol() {
        return "GERENTE";
    }
}
