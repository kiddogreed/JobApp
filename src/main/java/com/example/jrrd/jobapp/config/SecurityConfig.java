package com.example.jrrd.jobapp.config;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("adminpass"))
                .roles("ADMIN")
                .build();
        UserDetails jobseeker = User.builder()
                .username("jobseeker")
                .password(passwordEncoder.encode("jobseekerpass"))
                .roles("JOBSEEKER")
                .build();
        UserDetails employer = User.builder()
                .username("employer")
                .password(passwordEncoder.encode("employerpass"))
                .roles("EMPLOYER")
                .build();
        return new InMemoryUserDetailsManager(admin, jobseeker, employer);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");
        http
            .csrf(csrf -> csrf.disable())
            .cors(withDefaults())
            .authorizeHttpRequests(auth -> auth
                // JobApplication endpoints
                .requestMatchers(HttpMethod.POST, "/api/applications").hasRole("JOBSEEKER")
                .requestMatchers(HttpMethod.GET, "/api/applications/my").hasRole("JOBSEEKER")
                .requestMatchers(HttpMethod.GET, "/api/applications/job/**").hasAnyRole("EMPLOYER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/applications/*/status").hasAnyRole("EMPLOYER", "ADMIN")
                // Employer and admin can add, edit, delete jobs
                .requestMatchers(HttpMethod.POST, "/api/jobs").hasAnyRole("EMPLOYER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/jobs/**").hasAnyRole("EMPLOYER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/jobs/**").hasAnyRole("EMPLOYER", "ADMIN")
                // All roles can view jobs
                .requestMatchers(HttpMethod.GET, "/api/jobs/**").hasAnyRole("JOBSEEKER", "EMPLOYER", "ADMIN")
                // Admin can access everything else
                .anyRequest().hasRole("ADMIN")
            )
            .httpBasic(withDefaults());
        logger.info("Security filter chain configured successfully");
        return http.build();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
