package com.techsolutions.platform.observer;

import com.techsolutions.platform.model.Producto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PATRÓN OBSERVER - Subject
 * Gestor de inventario que notifica a observadores cuando hay stock bajo
 * RF5 y RF6: Notificar cuando stock < mínimo (configurable)
 */
@Service
public class GestorInventario {
    
    private final List<ObservadorInventario> observadores;
    private final Map<String, Producto> inventario;
    
    public GestorInventario() {
        this.observadores = new ArrayList<>();
        this.inventario = new HashMap<>();
        inicializarInventarioDemo();
    }
    
    private void inicializarInventarioDemo() {
        agregarProducto(new Producto("PROD-001", "Laptop Dell XPS", 
            "Laptop empresarial", 2500.00, 15, 10, "Electrónica"));
        agregarProducto(new Producto("PROD-002", "Mouse Logitech", 
            "Mouse inalámbrico", 45.00, 5, 20, "Accesorios"));
        agregarProducto(new Producto("PROD-003", "Monitor LG 27\"", 
            "Monitor Full HD", 350.00, 25, 15, "Electrónica"));
    }
    
    public void suscribir(ObservadorInventario observador) {
        observadores.add(observador);
        System.out.println("✅ Observador suscrito: " + observador.obtenerRol());
    }
    
    public void desuscribir(ObservadorInventario observador) {
        observadores.remove(observador);
        System.out.println("❌ Observador desuscrito: " + observador.obtenerRol());
    }
    
    public void notificarObservadores(Producto producto, String mensaje) {
        System.out.println("\n🔔 Notificando a " + observadores.size() + " observadores...");
        for (ObservadorInventario observador : observadores) {
            observador.notificar(producto, mensaje);
        }
    }
    
    public void agregarProducto(Producto producto) {
        inventario.put(producto.getId(), producto);
    }
    
    public void actualizarStock(String productoId, int cantidad) {
        Producto producto = inventario.get(productoId);
        if (producto == null) {
            System.out.println("❌ Producto no encontrado: " + productoId);
            return;
        }
        
        int stockAnterior = producto.getStock();
        producto.setStock(cantidad);
        
        System.out.println("\n📊 Stock actualizado:");
        System.out.println("   Producto: " + producto.getNombre());
        System.out.println("   Stock anterior: " + stockAnterior);
        System.out.println("   Stock actual: " + cantidad);
        
        // Verificar si requiere reposición
        if (producto.requiereReposicion()) {
            String mensaje = String.format(
                "⚠️ ALERTA: Stock bajo detectado para '%s'. " +
                "Stock actual (%d) está por debajo del mínimo (%d).",
                producto.getNombre(), producto.getStock(), producto.getStockMinimo()
            );
            notificarObservadores(producto, mensaje);
        }
    }
    
    public void configurarStockMinimo(String productoId, int nuevoMinimo) {
        Producto producto = inventario.get(productoId);
        if (producto != null) {
            producto.setStockMinimo(nuevoMinimo);
            System.out.println("✅ Stock mínimo actualizado para " + 
                producto.getNombre() + ": " + nuevoMinimo);
        }
    }
    
    public Map<String, Producto> obtenerInventario() {
        return new HashMap<>(inventario);
    }
    
    public Producto obtenerProducto(String productoId) {
        return inventario.get(productoId);
    }
}
