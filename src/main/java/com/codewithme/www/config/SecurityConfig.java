package com.codewithme.www.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.codewithme.www.security.JwtAuthenticationEntryPoint;
import com.codewithme.www.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtauthenticationfilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	public SecurityConfig(JwtAuthenticationFilter jwtauthenticationfilter,JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint)
	{
		this.jwtauthenticationfilter=jwtauthenticationfilter;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .exceptionHandling(exception ->
            exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
        )

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/api/auth",
                        "/api/auth/register",
                        "/api/auth/login",
                        "/api/auth/verify-email",
                        "/api/auth/upload-image",
                        "/api/auth/resend-verification",
                        "/actuator/health"
                ).permitAll()
                .anyRequest().authenticated()
            )

            .addFilterBefore(jwtauthenticationfilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

	@Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}