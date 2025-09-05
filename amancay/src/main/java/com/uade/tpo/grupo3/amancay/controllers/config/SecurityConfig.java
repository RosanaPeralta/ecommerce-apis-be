package com.uade.tpo.grupo3.amancay.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uade.tpo.grupo3.amancay.entity.Role;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req
                                                 .requestMatchers("/api/v1/auth/**").permitAll()

                                                 //Lectura: Ambos
                                                 .requestMatchers(HttpMethod.GET,
                                                        "/products/**",
                                                        "/categories/**",
                                                        "/activities/**",
                                                        "/product-images/**",
                                                        "/stock/**",
                                                        "/reviews/**")
                                                .hasAnyAuthority(Role.USER.name(), Role.SELLER.name())

                                                //Escritura: Solo vendedores
                                                 .requestMatchers(HttpMethod.POST,
                                                        "/products/**",
                                                        "/categories/**",
                                                        "/activities/**",
                                                        "/product-images/**",
                                                        "/discounts/**",
                                                        "/stock/**").hasAuthority(Role.SELLER.name())

                                                .requestMatchers(HttpMethod.PUT,
                                                        "/products/**",
                                                        "/categories/**",
                                                        "/activities/**",
                                                        "/product-images/**",
                                                        "/discounts/**",
                                                        "/stock/**",
                                                        "/orders/**").hasAuthority(Role.SELLER.name())

                                                .requestMatchers(HttpMethod.DELETE,
                                                        "/products/**",
                                                        "/categories/**",
                                                        "/activities/**",
                                                        "/product-images/**",
                                                        "/discounts/**",
                                                        "/stock/**",
                                                        "/orders/**")
                                                .hasAuthority(Role.SELLER.name())

                                                //Escritura: Solo usuarios
                                                .requestMatchers(HttpMethod.POST, 
                                                        "/reviews/**",
                                                         "/orders/**") //Comprar
                                                .hasAuthority(Role.USER.name())

                                                .requestMatchers(HttpMethod.PUT, "/reviews/**").hasAuthority(Role.USER.name())
                                                .requestMatchers(HttpMethod.DELETE, "/reviews/**").hasAuthority(Role.USER.name())

                                                 .anyRequest()
                                                 .authenticated()
                                                )
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
