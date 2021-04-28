package com.github.czechp.recruitmentapp.utility.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service()
public class JwtTokenService {
    //TODO: MAKE IT AS ENVIRONMENT VARIABLE
    private static final String SECRET_KEY = "1234567890";
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;
    private static final int EXPIRATION_MINUTES = 10;

    //TODO: return here and check SECRET KEY
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRATION_MINUTES))
                .signWith(ALGORITHM, TextCodec.BASE64.decode(SECRET_KEY))
                .compact();
    }

}
