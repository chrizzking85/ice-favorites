package com.example.demo.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    "basicScheme",
                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")
                )
            )
            .info(
                Info()
                    .title("Car Configuration API")
                    .version("1.0.0")
                    .description("This is a simple REST API")
            )
    }
}