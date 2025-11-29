package com.techsolutions.platform.controller;

import com.techsolutions.platform.command.*;
import com.techsolutions.platform.memento.*;
import com.techsolutions.platform.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller REST para gestión de pedidos (Patrones Command y Memento)
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {
    
    @Autowired
    private GestorComandos gestorComandos;
    
    @Autowired
    private GestorMementos gestorMementos;
    
    // Almacenamiento temporal de pedidos (en producción sería una BD)
    private final Map<String, Pedido> pedidos = new HashMap<>();
    
    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearPedido(@RequestBody Pedido pedido) {
        // Guardar memento antes de crear
        MementoPedido memento = new MementoPedido(pedido);
        gestorMementos.guardarMemento(pedido.getId(), memento);
        
        // Ejecutar comando
        ComandoCrearPedido comando = new ComandoCrearPedido(pedido);
        gestorComandos.ejecutarComando(comando);
        
        pedidos.put(pedido.getId(), pedido);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("pedido", pedido);
        respuesta.put("mensaje", "Pedido creado exitosamente");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/procesar/{pedidoId}")
    public ResponseEntity<Map<String, Object>> procesarPedido(@PathVariable String pedidoId) {
        Pedido pedido = pedidos.get(pedidoId);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Guardar memento antes de procesar
        MementoPedido memento = new MementoPedido(pedido);
        gestorMementos.guardarMemento(pedidoId, memento);
        
        // Ejecutar comando
        ComandoProcesarPedido comando = new ComandoProcesarPedido(pedido);
        gestorComandos.ejecutarComando(comando);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("pedido", pedido);
        respuesta.put("mensaje", "Pedido procesado");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/aplicar-descuento/{pedidoId}")
    public ResponseEntity<Map<String, Object>> aplicarDescuento(
            @PathVariable String pedidoId,
            @RequestParam double monto) {
        
        Pedido pedido = pedidos.get(pedidoId);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Guardar memento antes de aplicar descuento
        MementoPedido memento = new MementoPedido(pedido);
        gestorMementos.guardarMemento(pedidoId, memento);
        
        // Ejecutar comando
        ComandoAplicarDescuento comando = new ComandoAplicarDescuento(pedido, monto);
        gestorComandos.ejecutarComando(comando);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("pedido", pedido);
        respuesta.put("mensaje", "Descuento aplicado");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/cancelar/{pedidoId}")
    public ResponseEntity<Map<String, Object>> cancelarPedido(@PathVariable String pedidoId) {
        Pedido pedido = pedidos.get(pedidoId);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Guardar memento antes de cancelar
        MementoPedido memento = new MementoPedido(pedido);
        gestorMementos.guardarMemento(pedidoId, memento);
        
        // Ejecutar comando
        ComandoCancelarPedido comando = new ComandoCancelarPedido(pedido);
        gestorComandos.ejecutarComando(comando);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("pedido", pedido);
        respuesta.put("mensaje", "Pedido cancelado");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/deshacer")
    public ResponseEntity<Map<String, Object>> deshacer() {
        boolean exito = gestorComandos.deshacer();
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("exito", exito);
        respuesta.put("mensaje", exito ? "Comando deshecho" : "No hay comandos para deshacer");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/rehacer")
    public ResponseEntity<Map<String, Object>> rehacer() {
        boolean exito = gestorComandos.rehacer();
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("exito", exito);
        respuesta.put("mensaje", exito ? "Comando rehecho" : "No hay comandos para rehacer");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/historial")
    public ResponseEntity<List<String>> obtenerHistorial() {
        return ResponseEntity.ok(gestorComandos.obtenerHistorial());
    }
    
    @PostMapping("/restaurar/{pedidoId}")
    public ResponseEntity<Map<String, Object>> restaurarMemento(@PathVariable String pedidoId) {
        MementoPedido memento = gestorMementos.obtenerUltimoMemento(pedidoId);
        
        if (memento == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "No hay mementos para restaurar");
            return ResponseEntity.badRequest().body(error);
        }
        
        Pedido pedidoRestaurado = memento.restaurarPedido();
        pedidos.put(pedidoId, pedidoRestaurado);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("pedido", pedidoRestaurado);
        respuesta.put("mensaje", "Pedido restaurado desde memento");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/mementos/{pedidoId}")
    public ResponseEntity<List<String>> listarMementos(@PathVariable String pedidoId) {
        return ResponseEntity.ok(gestorMementos.listarMementos(pedidoId));
    }
    
    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable String pedidoId) {
        Pedido pedido = pedidos.get(pedidoId);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }
}
