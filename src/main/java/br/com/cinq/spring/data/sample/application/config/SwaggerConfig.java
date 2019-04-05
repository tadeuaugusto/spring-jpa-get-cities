package br.com.cinq.spring.data.sample.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// import static com.google.common.base.Predicate.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket detalheApi() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select().apis(RequestHandlerSelectors.basePackage("br.com.cinq.spring.data.sample.application")).paths(PathSelectors.any())
				.build().apiInfo(this.informacoesApi().build());

		return docket;
	}

	private ApiInfoBuilder informacoesApi() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		apiInfoBuilder.title("GET REST");
		apiInfoBuilder.description("API using Spring Boot with Java8 including H2 Database and Swagger2 for API documentation.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.license("License - Open Source");
		apiInfoBuilder.licenseUrl("https://github.com/tadeuaugusto/SpringJpaGetRest");
		apiInfoBuilder.contact(this.contato());

		return apiInfoBuilder;

	}

	private Contact contato() {

		return new Contact("Tadeu Augusto Dutra Pinto", "https://github.com/tadeuaugusto", "tadeuaugusto@gmail.com");
	}
	// @Bean
	// public Docket api() {
	// return new Docket(DocumentationType.SWAGGER_2)
	// .select()
	// .apis(RequestHandlerSelectors.any())
	// .paths(PathSelectors.any())
	// .build();
	// }
	//
	// private ApiInfo metaData() {
	// return new ApiInfoBuilder().title("Spring Boot REST API")
	// .description("\"Spring Boot REST API for GET Cities and
	// Countries\"").version("1.0.0")
	// .license("Apache License Version
	// 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	// .build();
	// }

}