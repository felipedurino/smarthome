package com.smarthome.backend_smarthome.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new java.util.Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }

    public String getEmailFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isTokenExpired(String token){
       try {
           Claims claims = Jwts.parserBuilder()
                   .setSigningKey(getSigningKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
           return claims.getExpiration().before(new java.util.Date());
       }catch (Exception e){
           return true;
       }
    }


}
