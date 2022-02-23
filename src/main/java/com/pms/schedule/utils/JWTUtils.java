package com.pms.schedule.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String SECRET;

    // retrieve username from jwt token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // retrieve expiration date from jwt token
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    //check if the token has expired
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //for retrieveing any information from token we will need the secret key
    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | ExpiredJwtException e) {
            throw e;
        }
    }

    //validate token
    public Boolean validateToken(String token) {
        return isTokenExpired(token);
    }
}
