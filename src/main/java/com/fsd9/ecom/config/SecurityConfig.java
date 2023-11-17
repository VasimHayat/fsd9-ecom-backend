package com.fsd9.ecom.config;

import com.fsd9.ecom.security.JwtAuthenticationEntryPoint;
import com.fsd9.ecom.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("CAAAAAAAAAAAAAa");
        httpSecurity.csrf(csrf -> csrf.disable())
                .cors(cors -> {
                    try {
                        cors.disable().cors().configurationSource(corsConfigurationSource());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                // .authorizeHttpRequests(auth ->
                //        auth
                //                .requestMatchers("/actuator/**").permitAll()
                //                .requestMatchers("/api/v1/auth/register").permitAll()
                //                .requestMatchers("/api/v1/auth/login").permitAll()
                //                .requestMatchers("/api/v1/user/**").permitAll()

                //                .requestMatchers("/api/v1/products/**").permitAll()

                //         .anyRequest().authenticated()
                // )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


    CorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");

        configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        AuthenticationManager am = builder.getAuthenticationManager();
        return am;
    }


}
