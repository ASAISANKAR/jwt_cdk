package com.springsecurity_jwt.jwt.Config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(request -> request
            .requestMatchers("/").permitAll()
            .requestMatchers(HttpMethod.POST, "/building")
            .hasAuthority("ROLE_ADMIN")
            .requestMatchers("/admin/**")
            .hasAuthority("ROLE_ADMIN")
            .anyRequest().authenticated())
            .exceptionHandling(exception -> exception
            .authenticationEntryPoint((request, response, authException) -> {
              response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            })
            .accessDeniedHandler((request, response, accessDeniedException) -> {
              response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            })
            )
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults())
        .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();
  }


  @Bean
  public AuthenticationProvider authenticationProvider(UserDetailsService user){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
    provider.setUserDetailsService(user);
    return provider;
  }

}
