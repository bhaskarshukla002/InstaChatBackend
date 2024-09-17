package com.example.InstaChat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new ApplicationAuditAware();
    }

    @Bean
    public CorsConfiguration corsConfiguration() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow credentials
//        config.setAllowedOrigins(Arrays.asList("http://127.0.0.1:4500","http://127.0.0.1:5500")); // Exact origin
        config.setAllowedOrigins(Arrays.asList("http://127.0.0.1:4500","http://127.0.0.1:5500"));
        config.addAllowedHeader("*");

//        config.setAllowedHeaders(Arrays.asList(
//                HttpHeaders.ORIGIN,
//                HttpHeaders.CONTENT_TYPE,
//                HttpHeaders.ACCEPT,
//                HttpHeaders.AUTHORIZATION
//        ));
        config.addAllowedMethod("*");
//        config.setAllowedMethods(Arrays.asList(
//                "GET",
//                "POST",
//                "DELETE",
//                "PUT",
//                "PATCH"
//        ));

        source.registerCorsConfiguration("/**", config);
        return config;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration());
        return new CorsFilter(source);
    }

}