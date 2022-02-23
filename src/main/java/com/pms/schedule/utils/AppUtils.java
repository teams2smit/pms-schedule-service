package com.pms.schedule.utils;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AppUtils {

    @Autowired
    private JWTUtils jwtUtils;

    public String extractUsernameFromRequest(HttpServletRequest request) {
        final String requestHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;


        try {
            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                jwtToken = requestHeader.substring(7);
                username = jwtUtils.extractUsername(jwtToken);

                if (username != null) {
                    try {
                        if (jwtUtils.validateToken(jwtToken)) {
                            return username;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("User not Found");
                    }
                }

                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT Token has expired");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }
}
