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
		myContact.setName("Jane Doe");
		myContact.setEmail("your.email@gmail.com");

		Info information = new Info()
				.title("Employee Management System API")
				.version("1.0")
				.description("This API exposes endpoints to manage employees.")
				.contact(myContact);
		return new OpenAPI()
				.info(information)
				.addSecurityItem(new SecurityRequirement()
						.addList("Bearer Authentication"))

				.components(new Components().addSecuritySchemes
						("Bearer Authentication", createAPIKeyScheme()))
				.servers(List.of(server));
	}

	private SecurityScheme createAPIKeyScheme() {
		return new SecurityScheme().type(SecurityScheme.Type.HTTP)
				.bearerFormat("JWT")
				.scheme("bearer");
	}
/*
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder()
				.group("besysoft")
				.packagesToScan("besysoft.agendaApp.controller")
				.build();

		//.securitySchemes(securitySchemes());
	}


	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("A122R")
						.description("Aplicación Web para la emisión de comprobantes de agentes de retención")
						.version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))

				;
	}


*/

}
