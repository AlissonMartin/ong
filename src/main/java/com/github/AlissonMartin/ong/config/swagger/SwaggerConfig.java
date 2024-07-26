package com.github.AlissonMartin.ong.config.swagger;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public GroupedOpenApi publicApi() {
                return GroupedOpenApi.builder()
                        .group("public")
                        .pathsToMatch("/public/**", "/authentication/**", "/swagger-ui/**", "/v3/api-docs/**")
                        .build();
        }

        @Bean
        public GroupedOpenApi privateApi() {
                return GroupedOpenApi.builder()
                        .group("private")
                        .pathsToMatch("/organizations/**")
                        .build();
        }

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .components(new Components()
                                .addSecuritySchemes("bearerAuth",
                                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
                        )
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
        }
}