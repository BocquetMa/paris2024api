package bts.sio.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://172.20.177.251:8080/matheoparis2024");
        devServer.setDescription("Server URL in Development environment");

        Server devServer2 = new Server();
        devServer2.setUrl("https://prodtomcat.inforostand14.net/matheoparis2024");
        devServer2.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setName("Paris 2024 API Support");
        contact.setEmail("support@paris2024api.com");

        License license = new License()
                .name("Apache License 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("Documentation API Paris 2024")
                .version("1.0")
                .contact(contact)
                .description("Cette API fournit l'accès aux données des Jeux Olympiques de Paris 2024, incluant les athlètes, sports, épreuves et actualités.")
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, devServer2));
    }
}