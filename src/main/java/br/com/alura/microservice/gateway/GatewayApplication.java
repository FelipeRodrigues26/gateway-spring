package br.com.alura.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			.route("fornecedor_info", r -> r.path("/info/{id}")
				.uri("http://localhost:8081"))
			.route("fornecedor_pedido", r -> r.path("/pedido/**")
				.uri("http://localhost:8081"))
			.route("fornecedor_produto", r -> r.path("/produto/{id}")
					.uri("http://localhost:8081"))
			.route("loja_compra", r -> r.path("/compra/**")
					.uri("http://localhost:8082"))
			.route("transportador_entrega", r -> r.path("/entrega/**")
					.uri("http://localhost:8083"))
//
//			.route("rewrite_route", r -> r.host("*.rewrite.org")
//				.filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
//				.uri("http://httpbin.org"))
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
