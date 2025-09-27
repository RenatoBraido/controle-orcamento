package br.com.orcamento.controleorcamento.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica o CORS a todos os endpoints da sua API
                .allowedOrigins("http://localhost:63342") // PERMITE REQUISIÇÕES DESTA ORIGEM
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true); // Permite o envio de cookies, cabeçalhos de autenticação, etc.
    }
}