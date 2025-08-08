package com.example.HospitalManagment.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Value("")
    private String baseUrl;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
//
//        http.authorizeHttpRequests((authorize) -> {
//            // Allow all auth-related requests, such as registration
//            authorize.requestMatchers( "/" + baseUrl + "/auth/**").permitAll();
//            // Allow public endpoints
//            authorize.requestMatchers( "/" + baseUrl + "/category/all/**").permitAll();
//            authorize.requestMatchers( "/" + baseUrl + "/health").permitAll();
//            authorize.requestMatchers("/" + baseUrl + "/category/create/**").permitAll();
//            // User-specific routes that require authentication
//            authorize.requestMatchers("/" + baseUrl + "/user/password/**",
//                    "/" + baseUrl + "/user/me/**",
//                    "/" + baseUrl + "/ticket/get/**").authenticated();
//            // Any other route must be authenticated
//            authorize.anyRequest().authenticated();
//        });
//
//        // Handle unauthorized access
//        http.exceptionHandling( exception -> exception.authenticationEntryPoint(authenticationEntryPoint));
//        // Set session management to stateless (JWT doesn't need sessions)
//        http.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        // Set the authentication provider
//        http.authenticationProvider(authenticationProvider);
//        // Add JWT authentication filter before UsernamePasswordAuthenticationFilter
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // Disable authentication for all requests
        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
//TODO add for all request authentication with barer token like this

//        http.authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/api/profile/me").authenticated()
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    // CORS configuration
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("OPTIONS");
        configuration.addAllowedMethod("PATCH");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
