package ru.farmersregister.farmersregister.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Api для регистрации районов и фермеров",
        description = "Farmer Registration", version = "0.0.1",
        contact = @Contact(
            name = "Lupaev Sergej",
            email = "lupaevsv@gmail.com"
        )
    )
)
public class SwaggerConfig {


}
