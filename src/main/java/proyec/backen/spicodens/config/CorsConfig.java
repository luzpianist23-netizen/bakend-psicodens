// src/main/java/proyec/backen/spicodens/config/CorsConfig.java
package proyec.backen.spicodens.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                        // Permisivo para TODAS las rutas (todo el backend)
                .allowedOrigins("*")                      // Permite cualquier origen (incluyendo http://localhost:4200)
                .allowedMethods("*")                      // Permite TODOS los métodos (GET, POST, PUT, DELETE, OPTIONS, etc.)
                .allowedHeaders("*")                      // Permite TODOS los headers
                .exposedHeaders("*")                      // Expone TODOS los headers si es necesario
                .allowCredentials(false)                  // Desactiva credentials para simplicidad (cámbialo si usas auth con cookies)
                .maxAge(3600);                            // Cache del preflight por 1 hora para evitar requests extras
    }
}