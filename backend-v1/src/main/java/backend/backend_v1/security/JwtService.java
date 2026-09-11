package backend.backend_v1.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Service 
public class JwtService {

    // Importar variables desde application.properties 

    @Value("${jwt.secret}")
    private String secret_key;

    // Método que genera clave criptográfica simétrica para firmar tokens

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret_key.getBytes());

    } // getSigninKey

    // Método que permite generar el token para el usuario 

    public String generarAccessToken(UserDetails userDetails) {
        return Jwts.builder()
            .subject(userDetails.getUsername())
            .claim("type", "access")
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
            .signWith(getSigningKey())
            .compact();

        /*

        1. Coge el username, el usuario en la aplicación. 
        2. Indica la fecha de creación del token
        3. Indica la fecha de expiración del token
        4. Firma con la clave criptográfica generada para el token
        5. Lo junta todo y devuelve el token generado

        */

    } // generateToken

    // Método que permite generar el refresh token 

    public String generarRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
            .subject(userDetails.getUsername())
            .claim("type", "refresh")
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7))
            .signWith(getSigningKey())
            .compact();
    }

    // Método para obtener el usuario a partir de un token 

    public String extraerUsername(String token) {
        return extraerClaims(token, Claims::getSubject);
    }

    // Método para saber si el token ha expirado 

    private boolean tokenHaExpirado(String token) {
        return extraerFechaExpiracionToken(token).before(new Date());

    }

    // Método para obtener la fecha de expiración del token

    private Date extraerFechaExpiracionToken(String token) {
        return extraerClaims(token, Claims::getExpiration);

    }

    // Método para saber si el token es válido 

    public boolean tokenEsValido(String token, UserDetails userDetails) {
        String username = extraerUsername(token);
        return username.equals(userDetails.getUsername()) && !tokenHaExpirado(token);

    }

    // Método para saber si el token es de tipo refresh

    public boolean esRefreshToken(String token) {
        String tipo = extraerClaims(
            token, 
            claims -> claims.get("type", String.class)
        );

        return "refresh".equals(tipo);

    }

    // Método para extraer los claims del token

    private <T> T extraerClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return claimsResolver.apply(claims);

    } // extraerClaims

} // class