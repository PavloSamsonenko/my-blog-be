package com.my.blog.myblogbe.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/** Configuration class for OpenAPI. */
@Configuration
@OpenAPIDefinition(info = @Info(title = "MyBlog Backapi", version = "0.0.1"))
@SecurityScheme(
    name = "Authorization",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer")
public class OpenApiConfig {}
