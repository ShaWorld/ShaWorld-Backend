package com.dsm.shaworld.global.authorization;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${auth.jwt.secret}")
    private String secretKey;

    private final long JWT_ACCESS_EXPIRATION = 10 * 60 * 6 * 24; // 1일

    public String generateAccessToken(String userEmail) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_EXPIRATION * 1000))
                .setSubject(userEmail)
                .claim("type", "access_token")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String getId(String token) {
        return String.valueOf(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
    }
}