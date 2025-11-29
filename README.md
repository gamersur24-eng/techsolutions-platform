
## 📋 Descripción del Proyecto

TechSolutions Platform es un sistema integral desarrollado para pequeñas y medianas empresas (PYMEs) que implementa **6 patrones de diseño** para resolver problemas reales de gestión empresarial:

- 💳 **Procesamiento de pagos** con múltiples pasarelas
- 🔒 **Control de acceso** a información sensible
- 📦 **Gestión de inventario** con notificaciones automáticas
- 📝 **Procesamiento de pedidos** con historial reversible
- 💰 **Políticas de precios** dinámicas
- 📚 **Catálogo de productos** eficiente

**Proyecto Final - Patrones de Diseño de Software**  
**Institución:** IDAT  
**Curso:** Análisis y Diseño de Sistemas  
**Año:** 2025

---

## 🛠️ Tecnologías Utilizadas

- **Backend:** Spring Boot 3.1.5
- **Lenguaje:** Java 17
- **Build Tool:** Maven 3.8+
- **Librerías:** Lombok
- **Arquitectura:** REST API

---

## 📁 Estructura del Proyecto

```
techsolutions-platform/
├── src/
│   ├── main/
│   │   ├── java/com/techsolutions/platform/
│   │   │   ├── adapter/        ← Patrón Adapter (Pasarelas de pago)
│   │   │   ├── proxy/          ← Patrón Proxy (Control de acceso)
│   │   │   ├── observer/       ← Patrón Observer (Notificaciones)
│   │   │   ├── command/        ← Patrón Command (Comandos reversibles)
│   │   │   ├── memento/        ← Patrón Memento (Restauración de estado)
│   │   │   ├── strategy/       ← Patrón Strategy (Estrategias de precios)
│   │   │   ├── iterator/       ← Patrón Iterator (Navegación de catálogo)
│   │   │   ├── model/          ← Modelos de dominio
│   │   │   ├── controller/     ← REST Controllers
│   │   │   └── config/         ← Configuración
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## 🎯 Patrones de Diseño Implementados

### 1️⃣ **Adapter** - Integración de Pasarelas de Pago
Unifica las interfaces de PayPal, Yape y Plin bajo una interfaz común.

**Clases principales:**
- `ProcesadorPago` (interfaz)
- `PayPalAdapter`, `YapeAdapter`, `PlinAdapter`
- `GestorPasarelasPago`

### 2️⃣ **Proxy** - Control de Acceso a Reportes
Controla el acceso a reportes financieros según roles de usuario.

**Clases principales:**
- `ServicioReportes` (interfaz)
- `ServicioReportesReal`
- `ProxyServicioReportes`

### 3️⃣ **Observer** - Notificaciones de Inventario
Notifica automáticamente cuando el stock cae por debajo del mínimo.

**Clases principales:**
- `ObservadorInventario` (interfaz)
- `ObservadorGerente`, `ObservadorCompras`
- `GestorInventario`

### 4️⃣ **Command** - Operaciones Reversibles
Encapsula acciones de pedidos como objetos para permitir deshacer/rehacer.

**Clases principales:**
- `ComandoPedido` (interfaz)
- `ComandoCrearPedido`, `ComandoAplicarDescuento`, `ComandoCancelarPedido`
- `GestorComandos`

### 5️⃣ **Memento** - Captura de Estado
Guarda y restaura el estado de pedidos sin violar encapsulación.

**Clases principales:**
- `MementoPedido`
- `GestorMementos`

### 6️⃣ **Strategy** - Estrategias de Precios
Permite cambiar dinámicamente entre diferentes políticas de precios.

**Clases principales:**
- `EstrategiaPrecio` (interfaz)
- `PrecioEstandar`, `PrecioConDescuento`, `PrecioDinamico`
- `CalculadoraPrecios`

### 7️⃣ **Iterator** - Navegación del Catálogo
Permite recorrer productos con paginación y filtros.

**Clases principales:**
- `IteradorProductos` (interfaz)
- `IteradorProductosPaginado`
- `CatalogoProductos`

---

## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java 17 o superior
- Maven 3.8+
- IDE (IntelliJ IDEA recomendado)

### Pasos

1. **Clonar el repositorio**
```bash
git clone https://github.com/TU-USUARIO/techsolutions-platform.git
cd techsolutions-platform
```

2. **Compilar el proyecto**
```bash
mvn clean install
```

3. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

O desde tu IDE:
- Abrir el proyecto en IntelliJ IDEA
- Ejecutar `PlatformApplication.java`

4. **Verificar que está corriendo**
- La aplicación iniciará en: `http://localhost:8081`
- Página de inicio: `http://localhost:8081`

---

## 📡 API Endpoints

### Adapter - Procesamiento de Pagos
```http
POST /api/pagos/procesar?pasarela=PAYPAL&monto=100&referencia=TEST-001
GET  /api/pagos/configuracion/estado
```

### Proxy - Control de Acceso a Reportes
```http
GET /api/reportes/completo/RPT-001?usuarioId=USER-001&rol=GERENTE
GET /api/reportes/resumen/RPT-001
```

### Observer - Gestión de Inventario
```http
POST /api/inventario/suscribir/gerente?nombre=Carlos&email=gerente@tech.com
PUT  /api/inventario/actualizar-stock?productoId=PROD-002&cantidad=5
GET  /api/inventario/todos
```

### Command - Gestión de Pedidos
```http
POST /api/pedidos/crear
POST /api/pedidos/procesar?pedidoId=PED-001
POST /api/pedidos/aplicar-descuento?pedidoId=PED-001&porcentaje=20
POST /api/pedidos/deshacer
POST /api/pedidos/rehacer
GET  /api/pedidos/historial
```

### Strategy - Estrategias de Precios
```http
GET /api/precios/estrategia/actual
PUT /api/precios/estrategia/DESCUENTO?porcentaje=20
GET /api/precios/calcular/PROD-001
```

### Iterator - Catálogo de Productos
```http
GET /api/catalogo/categorias
GET /api/catalogo/listar?elementosPorPagina=5
GET /api/catalogo/pagina/1?elementosPorPagina=3
GET /api/catalogo/filtrar?categoria=Electrónica
GET /api/catalogo/buscar?termino=laptop
```

---

## 🧪 Pruebas

El proyecto incluye el archivo `PRUEBAS_API.http` con más de 50 ejemplos de peticiones para probar todos los endpoints.

Para ejecutar las pruebas:
1. Abrir `PRUEBAS_API.http` en IntelliJ IDEA
2. Hacer clic en "Run" al lado de cada petición

---

## 📋 Requerimientos Funcionales Cubiertos

| RF | Descripción | Patrón | Estado |
|----|-------------|--------|--------|
| RF1-RF2 | Integración de pasarelas de pago | Adapter | ✅ |
| RF3-RF4 | Control de acceso a reportes | Proxy | ✅ |
| RF5-RF6 | Notificaciones de inventario | Observer | ✅ |
| RF7-RF8 | Gestión de pedidos con historial | Command + Memento | ✅ |
| RF9-RF10 | Estrategias de precios | Strategy | ✅ |
| RF11-RF12 | Navegación del catálogo | Iterator | ✅ |

---




