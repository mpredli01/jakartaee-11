package org.redlich.jwtbridge.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * <p>JWTUtils class.</p>
 *
 * @author mpredli01
 */
public class JWTUtils {
    private static final String SECRET_KEY;

    static {
        final SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[32]; // 256 bits
        secureRandom.nextBytes(bytes);
        SECRET_KEY = Base64.getEncoder().encodeToString(bytes);
        }

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    /**
     * <p>Default constructor.</p>
     */
    public JWTUtils() {
        }
        
    /**
     * <p>generateToken.</p>
     *
     * @param username a {@link java.lang.String} object
     * @param roles a {@link java.util.List} object
     * @return a {@link java.lang.String} object
     */
    public static String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuer("payara")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        }

    /**
     * <p>decodeToken.</p>
     *
     * @param token a {@link java.lang.String} object
     * @return a {@link io.jsonwebtoken.Claims} object
     */
    public static Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        }
    }
