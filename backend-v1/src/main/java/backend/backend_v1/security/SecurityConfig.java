package backend.backend_v1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity 
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
        
    }

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(
                    SessionCreationPolicy.IF_REQUIRED
                )
            )
            .authorizeHttpRequests(auth -> auth

                // Inicio de sesión administrador público 

                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/logout").authenticated()
                .requestMatchers("/api/auth/me").authenticated()

                // Rutas públicas en la aplicación

                .requestMatchers("/api/productos/**").permitAll()
                .requestMatchers("/api/colecciones/**").permitAll()

                // Rutas administrativas dentro de la petición 

                .requestMatchers("/api/usuarios/crear").permitAll()
                .requestMatchers("/api/usuarios/**").authenticated()
                .requestMatchers("/api/usuarios/admin/**").authenticated()

                // Resto de peticiones públicas 

                .anyRequest().permitAll()

            );

        return http.build();

    }

} // class