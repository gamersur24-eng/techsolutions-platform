package com.techsolutions.platform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Modelo de Reporte Financiero sensible
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteFinanciero {
    private String id;
    private String titulo;
    private double ingresoTotal;
    private double gastoTotal;
    private double utilidadNeta;
    private String periodo;
    private LocalDateTime fechaGeneracion;
    private String contenidoDetallado;
}
