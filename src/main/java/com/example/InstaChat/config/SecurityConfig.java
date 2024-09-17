package com.example.InstaChat.config;

import com.example.InstaChat.auth.JwtAuthenticationEntryPoint;
import com.example.InstaChat.auth.JwtFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Autowired
    private  JwtFilter jwtAuthFilter;
    @Autowired
    private  AuthenticationProvider authenticationProvider;

    @Autowired private JwtAuthenticationEntryPoint point;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(withDefaults())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeRequests(req -> req
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/home/**").permitAll()
                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/ws").permitAll()
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(new OncePerRequestFilter() {
                    @Override
                    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                        System.out.println("Request URI: " + request.getRequestURI());
                        System.out.println("Request Headers: ");
                        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                                System.out.println(headerName + ": " + request.getHeader(headerName))
                        );
                        filterChain.doFilter(request, response);
                    }
                }, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
