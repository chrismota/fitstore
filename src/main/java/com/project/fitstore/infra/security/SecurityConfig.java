package com.project.fitstore.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    final UserDetailsServiceImpl userDetailsService;
    final JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/customers/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/customers/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/orders/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/orders/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/payments/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/payments/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/coupons/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/customers/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/products/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/products/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/orders/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/products/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/orders/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/orders/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/payments/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/coupons/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/coupons/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/coupons/**").hasAuthority("ADMIN")
                                .anyRequest().denyAll()
                ).userDetailsService(userDetailsService)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
