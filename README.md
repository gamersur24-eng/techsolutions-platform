# 🚀 TechSolutions Platform

Sistema de Gestión Empresarial con Implementación de 6 Patrones de Diseño

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📋 Descripción del Proyecto

TechSolutions Platform es un sistema integral desarrollado para pequeñas y medianas empresas que implementa **6 patrones de diseño** (Adapter, Proxy, Observer, Command, Memento, Strategy, Iterator) para resolver problemas reales de gestión empresarial.

**Proyecto Final - Patrones de Diseño de Software**  
**Institución:** IDAT  
**Curso:** Análisis y Diseño de Sistemas

## Tecnologías

- Spring Boot 3.1.5
- Java 17
- Maven 3.8+
- Lombok

## Estructura del Proyecto

```
techsolutions-platform/
├── docs/
│   └── diagramas/              (7 Diagramas UML)
├── src/
│   ├── main/java/
│   │   └── com/techsolutions/platform/
│   │       ├── adapter/        (Patrón Adapter)
│   │       ├── proxy/          (Patrón Proxy)
│   │       ├── observer/       (Patrón Observer)
│   │       ├── command/        (Patrón Command)
│   │       ├── memento/        (Patrón Memento)
│   │       ├── strategy/       (Patrón Strategy)
│   │       ├── iterator/       (Patrón Iterator)
│   │       ├── model/          (Modelos de dominio)
│   │       └── controller/     (REST Controllers)
│   └── resources/
├── pom.xml
├── EXPLICACION_PATRONES.md
├── PRUEBAS_API.http
├── Reporte-TechSolutions-Patrones-Diseño.docx
└── Presentacion-TechSolutions.pptx
```

## Patrones Implementados

1. **Adapter** - Unifica interfaces de pasarelas de pago
2. **Proxy** - Controla acceso a reportes financieros
3. **Observer** - Notificaciones automáticas de inventario
4. **Command** - Operaciones reversibles en pedidos
5. **Memento** - Captura y restaura estado de pedidos
6. **Strategy** - Políticas de precios dinámicas
7. **Iterator** - Navegación eficiente del catálogo

## Cómo Ejecutar

1. Abrir el proyecto en IntelliJ IDEA
2. Ejecutar `PlatformApplication.java`
3. La aplicación iniciará en `http://localhost:8080`

## Endpoints Principales

- `/api/pagos/*` - Gestión de pagos
- `/api/reportes/*` - Reportes financieros
- `/api/inventario/*` - Gestión de inventario
- `/api/pedidos/*` - Gestión de pedidos
- `/api/precios/*` - Cálculo de precios
- `/api/catalogo/*` - Navegación del catálogo

## Documentación

- **Diagramas UML**: `docs/diagramas/`
- **Explicación de Patrones**: `EXPLICACION_PATRONES.md`
- **Ejemplos de API**: `PRUEBAS_API.http`
- **Reporte Completo**: `Reporte-TechSolutions-Patrones-Diseño.docx`
- **Presentación**: `Presentacion-TechSolutions.pptx`

## Requerimientos Funcionales Cubiertos

- RF1-RF2: Integración de pasarelas de pago
- RF3-RF4: Control de acceso a reportes
- RF5-RF6: Notificaciones de inventario
- RF7-RF8: Gestión de pedidos con historial
- RF9-RF10: Estrategias de precios
- RF11-RF12: Navegación del catálogo

## Autor

Omar - Curso de Análisis y Diseño de Sistemas
Universidad - Noviembre 2024
