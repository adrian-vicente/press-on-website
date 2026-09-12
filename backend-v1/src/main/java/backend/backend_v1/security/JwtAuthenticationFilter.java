package backend.backend_v1.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Inyección de dependencias

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = customUserDetailsService;

    }

    // Implementación de método de la interfaz

    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        // Obtener el header de la petición 

        final String authHeader = request.getHeader("Authorization");

        // Comprobación para ver si la petición lleva o no token 

        if(authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;

        } // if

        // Obtener el token del header de la petición 

        final String token = authHeader.substring(7);

        // Capturar excepciones en caso de accessToken caducado, intentar con refresh token 

        try {
            final String username_email = jwtService.extraerUsername(token);
            if(username_email != null && 
                SecurityContextHolder.getContext().getAuthentication() != null
            ) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username_email);

                if(jwtService.tokenEsValido(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username_email, null, userDetails.getAuthorities());
                    authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                            .buildDetails(request)
                    );

                    SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
                        
                } // if

            } // if

        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());

        } // try - catch

        filterChain.doFilter(request, response);

    }

} // class