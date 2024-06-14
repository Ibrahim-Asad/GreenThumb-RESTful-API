package com.greenthumb.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("GreenThumb API")
                        .description("GreenThumb application API documentation")
                        .version("1.0.0")
                        .contact(new Contact().name("Ibrahim Assad, Ahmad Alahmadneh, Khaled Sholi")
                                .email("i.a.s.assad33@gmail.com, alahamadneh35@gmail.com, Khaled.sholi2@gmail.com")
                                .url("https://github.com/Ibrahim-Asad/GreenThumb-RESTful-API"))
                        .license(new License().name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("GreenThumb Wiki Documentation")
                        .url("https://greenthumb.wiki.github.org/docs"));
    }
}
