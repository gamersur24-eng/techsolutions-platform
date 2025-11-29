package com.techsolutions.platform.controller;

import com.techsolutions.platform.adapter.GestorPasarelasPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller REST para gestión de pasarelas de pago (Patrón Adapter)
 */
@RestController
@RequestMapping("/api/pagos")
public class PagosController {
    
    @Autowired
    private GestorPasarelasPago gestorPasarelas;
    
    @PostMapping("/procesar")
    public ResponseEntity<Map<String, Object>> procesarPago(
            @RequestParam String pasarela,
            @RequestParam double monto,
            @RequestParam String referencia) {
        
        boolean exito = gestorPasarelas.procesarPago(pasarela, monto, referencia);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("exito", exito);
        respuesta.put("pasarela", pasarela);
        respuesta.put("monto", monto);
        respuesta.put("referencia", referencia);
        respuesta.put("mensaje", exito ? "Pago procesado exitosamente" : "Error al procesar pago");
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/configuracion/habilitar/{pasarela}")
    public ResponseEntity<Map<String, String>> habilitarPasarela(@PathVariable String pasarela) {
        gestorPasarelas.habilitarPasarela(pasarela.toUpperCase());
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Pasarela " + pasarela + " habilitada");
        respuesta.put("pasarela", pasarela);
        
        return ResponseEntity.ok(respuesta);
    }
    
    @PostMapping("/configuracion/deshabilitar/{pasarela}")
    public ResponseEntity<Map<String, String>> deshabilitarPasarela(@PathVariable String pasarela) {
        gestorPasarelas.deshabilitarPasarela(pasarela.toUpperCase());
        
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Pasarela " + pasarela + " deshabilitada");
        respuesta.put("pasarela", pasarela);
        
        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/configuracion/estado")
    public ResponseEntity<Map<String, Boolean>> obtenerEstadoPasarelas() {
        return ResponseEntity.ok(gestorPasarelas.obtenerEstadoPasarelas());
    }
}
