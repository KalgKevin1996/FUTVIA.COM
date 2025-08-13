package com.futvia.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi futviaApi() {
        return GroupedOpenApi.builder()
                .group("futvia")
                .pathsToMatch("/api/**")
        .addOpenApiCustomizer(openApi -> openApi.setInfo(
                new Info().title("Futvia API").version("v1").description("APIs Futvia")))
                .build();
    }
}
