package com.techsolutions.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación TechSolutions Platform
 * Implementa 6 patrones de diseño para gestión empresarial
 */
@SpringBootApplication
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("TechSolutions Platform - Iniciado");
        System.out.println("Puerto: 8080");
        System.out.println("Swagger UI: http://localhost:8080");
        System.out.println("===========================================\n");
    }
}
