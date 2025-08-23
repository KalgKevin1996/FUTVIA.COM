package com.futvia.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI futviaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Futvia API")
                        .version("v1")
                        .description("APIs para Futvia: gestión de ligas, equipos, jugadores y seguridad JWT.")
                        .license(new License().name("Apache 2.0").url("https://apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(new Server().url("/")));
    }
}
