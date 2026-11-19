package br.com.hyagorodrigues.movieflix.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {

        Contact contact = new Contact();
        contact.name("Hyago Rodrigues");
        contact.url("https://github.com/hyago-rdgs");

        Info info = new Info();
        info.title("MovieFlix");
        info.version("v1");
        info.description("Application for movie catalog management");
        info.contact(contact);

        return new OpenAPI().info(info);
    }
}
