package com.example.jrrd.jobapp.config;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.jrrd.jobapp.repository.UserRepository;
import com.example.jrrd.jobapp.model.User;
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
        // For development only: allows plain text passwords
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return username -> {
            User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
            return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");
        http
            .csrf(csrf -> csrf.disable())
            .cors(withDefaults())
            .authorizeHttpRequests(auth -> auth
                // Allow H2 console access
                .requestMatchers("/h2-console/**").permitAll()
                // Allow public access to root URL
                .requestMatchers("/").permitAll()
                // JobApplication endpoints
                .requestMatchers(HttpMethod.POST, "/api/applications").hasRole("JOBSEEKER")
                .requestMatchers(HttpMethod.GET, "/api/applications/my").hasRole("JOBSEEKER")
                .requestMatchers(HttpMethod.GET, "/api/applications/job/**").hasAnyRole("EMPLOYER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/applications/*/status").hasAnyRole("EMPLOYER", "ADMIN")
                // Profile endpoints
                .requestMatchers(HttpMethod.GET, "/api/profiles").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/profiles/{username}").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/profiles/{username}/me").authenticated()
                // Employer and admin can add, edit, delete jobs
                .requestMatchers(HttpMethod.POST, "/api/jobs").hasAnyRole("EMPLOYER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/jobs/**").hasAnyRole("EMPLOYER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/jobs/**").hasAnyRole("EMPLOYER", "ADMIN")
                // All roles can view jobs
                .requestMatchers(HttpMethod.GET, "/api/jobs/**").hasAnyRole("JOBSEEKER", "EMPLOYER", "ADMIN")
                // Admin can access everything else
                .anyRequest().hasRole("ADMIN")
            )
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Allow frames for H2 console
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
