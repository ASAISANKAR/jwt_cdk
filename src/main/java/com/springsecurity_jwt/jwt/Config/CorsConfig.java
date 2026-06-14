package com.springsecurity_jwt.jwt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class CorsConfig {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {

      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("https://grand-scone-06657a.netlify.app/", "http://localhost:3000")
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
      }
    };
  }
}