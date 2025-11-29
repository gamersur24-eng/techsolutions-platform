package com.techsolutions.platform.iterator;

import com.techsolutions.platform.model.Producto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PATRÓN ITERATOR - Aggregate
 * Catálogo de productos que proporciona iteradores
 * RF12: Mostrar productos sin exponer estructura interna
 */
@Service
public class CatalogoProductos {
    
    private final List<Producto> productos;
    
    public CatalogoProductos() {
        this.productos = new ArrayList<>();
        inicializarCatalogo();
    }
    
    private void inicializarCatalogo() {
        // Inicializar catálogo con productos de ejemplo
        productos.add(new Producto("CAT-001", "Laptop Dell XPS 15", 
            "Laptop empresarial de alto rendimiento", 2500.00, 15, 10, "Electrónica"));
        productos.add(new Producto("CAT-002", "Mouse Logitech MX Master", 
            "Mouse inalámbrico ergonómico", 95.00, 50, 20, "Accesorios"));
        productos.add(new Producto("CAT-003", "Monitor LG UltraWide 34\"", 
            "Monitor curvo 21:9", 650.00, 25, 15, "Electrónica"));
        productos.add(new Producto("CAT-004", "Teclado Mecánico Keychron", 
            "Teclado mecánico RGB", 120.00, 30, 10, "Accesorios"));
        productos.add(new Producto("CAT-005", "Webcam Logitech C920", 
            "Webcam Full HD 1080p", 85.00, 40, 15, "Accesorios"));
        productos.add(new Producto("CAT-006", "Impresora HP LaserJet", 
            "Impresora láser monocromática", 350.00, 12, 8, "Oficina"));
        productos.add(new Producto("CAT-007", "Router TP-Link AC1750", 
            "Router inalámbrico dual band", 75.00, 35, 20, "Redes"));
        productos.add(new Producto("CAT-008", "Disco Duro SSD Samsung 1TB", 
            "SSD NVMe alta velocidad", 180.00, 45, 25, "Almacenamiento"));
        productos.add(new Producto("CAT-009", "Auriculares Sony WH-1000XM5", 
            "Auriculares con cancelación de ruido", 380.00, 20, 10, "Audio"));
        productos.add(new Producto("CAT-010", "Tablet iPad Air", 
            "Tablet 10.9\" 256GB", 750.00, 18, 12, "Electrónica"));
    }
    
    public IteradorProductos crearIterador(int elementosPorPagina) {
        return new IteradorProductosPaginado(new ArrayList<>(productos), elementosPorPagina);
    }
    
    public IteradorProductos crearIteradorFiltrado(String categoria, int elementosPorPagina) {
        List<Producto> productosFiltrados = productos.stream()
            .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
            .collect(Collectors.toList());
        
        return new IteradorProductosPaginado(productosFiltrados, elementosPorPagina);
    }
    
    public IteradorProductos crearIteradorBusqueda(String termino, int elementosPorPagina) {
        List<Producto> productosEncontrados = productos.stream()
            .filter(p -> p.getNombre().toLowerCase().contains(termino.toLowerCase()) ||
                        p.getDescripcion().toLowerCase().contains(termino.toLowerCase()))
            .collect(Collectors.toList());
        
        return new IteradorProductosPaginado(productosEncontrados, elementosPorPagina);
    }
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("✅ Producto agregado al catálogo: " + producto.getNombre());
    }
    
    public List<String> obtenerCategorias() {
        return productos.stream()
            .map(Producto::getCategoria)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }
    
    public int getTotalProductos() {
        return productos.size();
    }
}
