package com.nader.scrum.management.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {
    private static final  String SECRECT_KEY="48404D635166546A576E5A7234753778214125442A472D4A614E645267556B58";

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken,Claims::getSubject);
    }

    public <T> T extractClaim (String jwtToken, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwToken)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keysBytes= Decoders.BASE64.decode(SECRECT_KEY);
        return Keys.hmacShaKeyFor(keysBytes);
    }
}
