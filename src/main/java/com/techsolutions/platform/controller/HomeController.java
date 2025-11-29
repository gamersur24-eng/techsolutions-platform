package com.techsolutions.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>TechSolutions Platform</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            min-height: 100vh;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            padding: 20px;
                        }
                        .container {
                            background: white;
                            padding: 50px;
                            border-radius: 20px;
                            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                            max-width: 800px;
                            text-align: center;
                        }
                        h1 {
                            color: #667eea;
                            font-size: 3em;
                            margin-bottom: 20px;
                        }
                        .subtitle {
                            color: #666;
                            font-size: 1.3em;
                            margin-bottom: 30px;
                        }
                        .status {
                            background: #d4edda;
                            color: #155724;
                            padding: 15px;
                            border-radius: 10px;
                            margin: 30px 0;
                            font-weight: bold;
                            font-size: 1.1em;
                        }
                        .status-icon {
                            font-size: 1.5em;
                            margin-right: 10px;
                        }
                        .endpoints {
                            text-align: left;
                            background: #f8f9fa;
                            padding: 20px;
                            border-radius: 10px;
                            margin: 30px 0;
                        }
                        .endpoints h3 {
                            color: #667eea;
                            margin-bottom: 15px;
                        }
                        .endpoint {
                            background: white;
                            padding: 10px;
                            margin: 10px 0;
                            border-radius: 5px;
                            font-family: 'Courier New', monospace;
                            font-size: 0.9em;
                            color: #333;
                        }
                        .method {
                            background: #667eea;
                            color: white;
                            padding: 3px 8px;
                            border-radius: 3px;
                            margin-right: 10px;
                            font-weight: bold;
                        }
                        .footer {
                            margin-top: 30px;
                            color: #999;
                            font-size: 0.9em;
                        }
                        a {
                            color: #667eea;
                            text-decoration: none;
                            font-weight: bold;
                        }
                        a:hover {
                            text-decoration: underline;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>🚀 TechSolutions Platform</h1>
                        <p class="subtitle">Sistema de Gestión Empresarial</p>
                        
                        <div class="status">
                            <span class="status-icon">✓</span>
                            Aplicación ejecutándose correctamente
                        </div>
                        
                        <div class="endpoints">
                            <h3>📋 Endpoints Disponibles</h3>
                            
                            <div class="endpoint">
                                <span class="method">GET</span>
                                <a href="/api/catalogo/categorias" target="_blank">/api/catalogo/categorias</a>
                            </div>
                            
                            <div class="endpoint">
                                <span class="method">GET</span>
                                <a href="/api/inventario/todos" target="_blank">/api/inventario/todos</a>
                            </div>
                            
                            <div class="endpoint">
                                <span class="method">GET</span>
                                <a href="/api/pagos/configuracion/estado" target="_blank">/api/pagos/configuracion/estado</a>
                            </div>
                            
                            <div class="endpoint">
                                <span class="method">GET</span>
                                <a href="/api/precios/estrategia/actual" target="_blank">/api/precios/estrategia/actual</a>
                            </div>
                        </div>
                        
                        <div style="background: #e7f3ff; padding: 20px; border-radius: 10px; margin-top: 20px;">
                            <h4 style="color: #0056b3; margin-bottom: 10px;">💡 Patrones Implementados</h4>
                            <p style="color: #333; line-height: 1.8;">
                                Adapter • Proxy • Observer • Command • Memento • Strategy • Iterator
                            </p>
                        </div>
                        
                        <div class="footer">
                            <p>TechSolutions S.A. - Análisis y Diseño de Sistemas</p>
                            <p>Spring Boot 3.1.5 • Java 17 • REST API</p>
                        </div>
                    </div>
                </body>
                </html>
                """;
    }
}
