package com.techsolutions.platform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de Usuario para control de accesos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String id;
    private String username;
    private String password;
    private String rol; // GERENTE, CONTADOR, COMPRAS, VENDEDOR
    private String email;
    private boolean activo;
}
