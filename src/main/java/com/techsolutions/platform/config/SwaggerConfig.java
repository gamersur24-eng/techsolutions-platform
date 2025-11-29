package com.techsolutions.platform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de Swagger/OpenAPI para documentación interactiva de la API
 * 
 * Acceder a la documentación en:
 * - Swagger UI: http://localhost:8080/swagger-ui.html
 * - OpenAPI JSON: http://localhost:8080/v3/api-docs
 */
@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI techSolutionsOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Servidor de desarrollo local");
        
        Contact contact = new Contact();
        contact.setName("TechSolutions S.A.");
        contact.setEmail("info@techsolutions.pe");
        contact.setUrl("https://techsolutions.pe");
        
        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");
        
        Info info = new Info()
                .title("TechSolutions Platform API")
                .version("1.0.0")
                .description("""
                        API REST de la Plataforma de Gestión Empresarial TechSolutions.
                        
                        Esta plataforma implementa 6 patrones de diseño para resolver problemas comunes 
                        en la gestión de pequeñas y medianas empresas:
                        
                        - **Adapter**: Integración de múltiples pasarelas de pago
                        - **Proxy**: Control de acceso a reportes financieros
                        - **Observer**: Notificaciones automáticas de inventario
                        - **Command**: Operaciones reversibles en pedidos
                        - **Memento**: Restauración de estados anteriores
                        - **Strategy**: Políticas de precios dinámicas
                        - **Iterator**: Navegación eficiente del catálogo
                        
                        **Desarrollado como proyecto académico para el curso de Análisis y Diseño de Sistemas**
                        """)
                .contact(contact)
                .license(license);
        
        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
