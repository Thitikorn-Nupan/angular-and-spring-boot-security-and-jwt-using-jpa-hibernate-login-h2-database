package com.ttknpdev.understandjwth2databasehelloworld.configuration.jwt;

import com.ttknpdev.understandjwth2databasehelloworld.log.Logging;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenUtil {

    // 1 h = 3600000 ms
    private final long JWT_TOKEN_VALIDITY = 3600000; // 1 hour
    private final Logging logging;
    @Value("${JWT.SECRET}")
    private String secret;

    public JwtTokenUtil() {
        logging = new Logging(this.getClass());
    }

    // validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        logging.logBack.info("validateToken() works");
        final String username = getUsernameFromToken(token);
        if (username.equals(userDetails.getUsername()) && !isTokenExpired(token)) {
            logging.logBack.info("User {} exists and token doesn't expire ",userDetails.getUsername());
            return true;
        } else {
            logging.logBack.warn("User {} did not exists" , userDetails.getUsername());
            return false;
        }
    }

    // retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver
                .apply(claims);
    }

    // retrieve any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        logging.logBack.info("getAllClaimsFromToken() works");
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    // check if the token has expired (v. หมดอายุแล้ว)
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration
                .before(new Date());
    }

    // retrieve expiration(การหมดอายุ) date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // generate token for user
    public String generateToken(UserDetails userDetails) {
        logging.logBack.info("generateToken() works");
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(
                claims,
                userDetails.getUsername()
        );
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        // Here, the doGenerateToken() method creates a JSON Web Token
        logging.logBack.info("doGenerateToken() works");
        System.out.println(System.currentTimeMillis());
        // issue (v. ออก)
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject) // Subject is combination of the username
                .setIssuedAt(new Date()) // The token is issued at the current date and time
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) // The token should expire after 1 h
                .signWith(SignatureAlgorithm.HS512, secret) // The token is signed using a secret key, which you can specify in the application.properties file or from system environment variable
                .compact();
    }

}
