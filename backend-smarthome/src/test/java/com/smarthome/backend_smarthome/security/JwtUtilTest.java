package com.smarthome.backend_smarthome.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "jwt.secret=mySecretKey123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890",
    "jwt.expiration=86400000"
})
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testGenerateToken() {
        String email = "teste@email.com";
        String token = jwtUtil.generateToken(email);
        
        assertNotNull(token);
        assertFalse(token.isEmpty());
        System.out.println("Token gerado: " + token);
    }

    @Test
    public void testValidateToken() {
        String email = "teste@email.com";
        String token = jwtUtil.generateToken(email);
        
        boolean isValid = jwtUtil.validateToken(token);
        assertTrue(isValid);
        System.out.println("Token válido: " + isValid);
    }

    @Test
    public void testGetEmailFromToken() {
        String email = "teste@email.com";
        String token = jwtUtil.generateToken(email);
        
        String extractedEmail = jwtUtil.getEmailFromToken(token);
        assertEquals(email, extractedEmail);
        System.out.println("Email extraído: " + extractedEmail);
    }

    @Test
    public void testIsTokenExpired() {
        String email = "teste@email.com";
        String token = jwtUtil.generateToken(email);
        
        boolean isExpired = jwtUtil.isTokenExpired(token);
        assertFalse(isExpired);
        System.out.println("Token expirado: " + isExpired);
    }
}
