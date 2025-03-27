package br.dev.JocaS2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfigure implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/api/**")
			.allowedOrigins("http://localhost:3000/", "http://localhost:3000/receita", "http://localhost:3000/cadastrado")
			.allowedMethods("GET", "POST", "DELETE", "PUT");

		// Add more mappings...
	}
}
