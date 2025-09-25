package com.mycompany.javafx.backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Admission System API")
                        .version("1.0.0")
                        .description("API REST para el sistema de admisiones (Postulantes, Documentos, Resultados, Admin)")
                        .contact(new Contact().name("Equipo de Desarrollo").email("dev@example.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Frontend integration guide")
                        .url("/docs/frontend-integration.html")
                );
    }
}
