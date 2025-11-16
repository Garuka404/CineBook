package com.cbs.cinebook.config;

import com.cbs.cinebook.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyclockRoleConverter());
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                       CorsConfiguration corsConfiguration = new CorsConfiguration();
                       corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                       corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                       corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                       corsConfiguration.setAllowCredentials(true);
                       corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
                       corsConfiguration.setMaxAge(3600L);
                       return corsConfiguration;
                    }
                }))
                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers("/non")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .requiresChannel(rcc->rcc.anyRequest().requiresInsecure())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/customer/all").hasAnyRole("ADMIN")
                        .requestMatchers("/not","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll());
        http.oauth2ResourceServer(rsc->rsc.jwt(jwtConfigurer->jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)));


        return http.build();
    }
}
