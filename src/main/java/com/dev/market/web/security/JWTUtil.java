package com.dev.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final String KEY = "12345";

    public String generateToken(UserDetails userDetail) {
        return Jwts.builder()
                .setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDet) {
        return userDet.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();//Subject es donde esta el usuario de la peticion vigente
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());//esta antes de la fecha actual true
    }

    //metodo que jala el cuerpo entero de JWT
    private Claims getClaims(String token) {
        return Jwts.parser().
                setSigningKey(KEY).
                parseClaimsJws(token).
                getBody();
    }
}
