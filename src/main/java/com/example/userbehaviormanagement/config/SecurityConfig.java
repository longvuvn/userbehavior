package com.example.userbehaviormanagement.config;

import com.example.userbehaviormanagement.utils.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/events/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/heatmap/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers("/api/v1/customers").permitAll()
                        .requestMatchers("/api/v1/reviews/product/{productId}").permitAll()
                        .requestMatchers("/data/image/**").permitAll()
                        .requestMatchers("/api/v1/event-types/**", "/api/v1/user-sessions/**", "/api/v1/event-logs/**", "/api/v1/admin"
                        ,"/api/v1/analysis-results", "/api/v1/heatmap/**", "/ws/analytics/**","/api/v1/reviews").permitAll()
                        .requestMatchers("/api/v1/orders/**").hasRole("Customer")
                        .requestMatchers("/api/v1/admin/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.POST, "/api/v1/products/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasRole("Admin")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
