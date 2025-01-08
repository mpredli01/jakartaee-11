package org.redlich.jwtbridge.api;

import org.redlich.jwtbridge.JwtAuthenticationMechanismDefinition;
import org.redlich.jwtbridge.util.JWTUtils;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.jsonwebtoken.Claims;

import java.util.List;

@Path("secured")
public class SecuredResource {

    @GET
    @Path("/hi-with-token")
    @Produces(MediaType.TEXT_PLAIN)
    @JwtAuthenticationMechanismDefinition(
            publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAtL6HShqY5H4y56rsCo7V\n" +
                    "dhT9/eLQwsJpKWg66j98XsB/qc5ZxkJ25GXCzpjR0ZvzAxMNlj1hrMORaKVzz2/5\n" +
                    "axZgF1eZfzgrNyQ9rtGaBtMNAB20jLsoYp5psRTaYxKeOiLHPr3956ukSRUF9YfJ\n" +
                    "GSamrvGOwC8h6zbq6uaydv+FVJXijlMD/iCggUfoirtVOWK/X1IzV7covxcGzT0X\n" +
                    "019/4RbtjLdnvqZnGqmpHQpBEItI+4gNvaKR8NDWUxAjO/v+oOKR5nEUnDWcQSCx\n" +
                    "KmyQrVJtHr9PBwWrHzTSx4k1L1hLf+AWXAdy/r6c0Lzgt5knmZTyWDG2+n8SlrXx\n" +
                    "HHxFO1Wz8H/OKBzTAf8zIuj2lkXYo+M6aoJM7qQmTys80dtYvnaHGSl+jpe2plMb\n" +
                    "S9RS4XcHH7vCqJc9acBnp9CvLgjOmA0b5Rc0WyN4sn1SDFYe6HZcVo4YGTbtTTlw\n" +
                    "gu/ozQ1x+xpTAaU0mWkHMwT0CO79rPORjhDXokEuduvtp6VUiAaoFF6Y3QQLf6O3\n" +
                    "P9p8yghpBBLb460lEQqOHQQGP0EK46cU81dlcD5lYE0TayDzb9pZZWUyjIE4Elzy\n" +
                    "W7wgI4xw7czdBalN+IhXKfGUCqIDVh7X7JpmskZMaRixf424yBcZLntEejZy59yL\n" +
                    "DSssHMc/bqnBraXuo8JBEPkCAwEAAQ==",
            publicKeyLocation = "/publicKey.pem",
            publicKeyAlgorithm = "ES256",
            decryptKeyLocation = "",
            decryptKeyAlgorithm = "RS256",
            issuer = "https://server.example.com",
            audiences = "s6BhdRkqt3,testing",
            header = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9",
            cookieName = "",
            tokenAge = 10,
            clockSkew = 10
            )
    public Response getSecuredHello(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || authHeader.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Include a token into the Header")
                    .build();
            }
        String token = authHeader.substring("Bearer".length()).trim();
        Claims claims = JWTUtils.decodeToken(token);
        String username = claims.getSubject();
        return Response.ok("Hey, " + username + "! You are connected to a secured endpoint.")
                .build();
        }

    @GET
    @Path("/admin-with-token")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSecuredAdmin(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || authHeader.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Include a token into the Header")
                    .build();
            }
        String token = authHeader.substring("Bearer".length()).trim();
        Claims claims = JWTUtils.decodeToken(token);
        List<String> roles = claims.get("roles", List.class);
        if (roles == null || !roles.contains("admin")) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You are not authorized to access this resource.")
                    .build();
            }
        String username = claims.getSubject();
        return Response.ok("Hey, " + username + "! You are connected to a secured endpoint as an ADMIN user.")
                .build();
        }
    }
