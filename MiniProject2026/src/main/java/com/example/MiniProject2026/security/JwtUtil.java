package com.example.MiniProject2026.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import javax.crypto.SecretKey;
import java.util.Base64;


@Component
public class JwtUtil {


    @Value("${jwt.secret}")
    public String secretkey;

    public SecretKey getSecretkey() {
        byte[] decoded= Base64.getDecoder().decode(secretkey);
        return Keys.hmacShaKeyFor(decoded);
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(getSecretkey())
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSecretkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token){
        return getClaims(token).getSubject();
    }
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }
    public boolean isTokenValid(String token , UserDetails userDetail){
        String username= getUsername(token);
                if(username.equals(userDetail.getUsername()) && !isTokenExpired(token)) return true;

                return false;
    }
}
