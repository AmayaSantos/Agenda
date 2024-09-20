package besysoft.agendaApp.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder()
				.group("besysoft")
				.packagesToScan("besysoft.agendaApp.controller")
				.build();

	}

	@Bean
	public OpenAPI defineOpenApi() {
		Server server = new Server();
		server.setUrl("http://localhost:8080");
		server.setDescription("Development");


		Contact myContact = new Contact();
		myContact.setName("falsa 123");
		myContact.setEmail("not.publicmail@gmail.com");

		Info information = new Info()
				.title("Agenda Web")
				.version("1.0")
				.description("La mejor agenda del mundo")
				.contact(myContact);
		return new OpenAPI()
				.info(information)
				.servers(List.of(server));
	}

}
