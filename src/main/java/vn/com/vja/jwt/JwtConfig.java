package vn.com.vja.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@PropertySource("classpath:config.properties")
public class JwtConfig {

    @Value("${internal.jwt.secret}")
    private String secret_key;

    @Value("${internal.jwt.expr}")
    private Long expr_time;


    public String generateToken(String username, @Nullable Map<String, String> claims) {

        return this.createToken(username, claims);
    }

    public String extractUsername(String token) {

        return extractToken(Claims::getSubject, token);
    }

    public Date extractExpiration(String token) {

        return extractToken(Claims::getExpiration, token);
    }

    public boolean validateToken(UserDetails userDetails, String token) {

        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && extractExpiration(token).after(new Date());
    }

    private <T> T extractToken(Function<Claims, T> fun, String token) {

        final Claims claims = getClaim(token);
        return fun.apply(claims);
    }

    private Claims getClaim(String token) {

        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret_key)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String createToken(String username, @Nullable final Map<String, String> claims) {

        return Jwts
                .builder()
                .claims(claims)
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + expr_time))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret_key)))
                .compact();
    }

}