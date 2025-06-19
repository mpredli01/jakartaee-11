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

/**
 * <p>SecuredResource class.</p>
 *
 * @author mpredli01
 */
@Path("secured")
public class SecuredResource {

    /**
     * <p>Default constructor.</p>
     */
    public SecuredResource() {
    }

    /**
     * <p>getSecuredHello.</p>
     *
     * @param authHeader a {@link java.lang.String} object
     * @return a {@link jakarta.ws.rs.core.Response} object
     */
    @GET
    @Path("/hi-with-token")
    @Produces(MediaType.TEXT_PLAIN)
    @JwtAuthenticationMechanismDefinition(
            publicKeyLocation = "/publicKey.pem",
            issuer = "https://server.example.com"
            )
    public Response getSecuredHello(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || authHeader.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("This is a secure endpoint. Please include a token into the header.")
                    .build();
            }
        String token = authHeader.substring("Bearer".length()).trim();
        Claims claims = JWTUtils.decodeToken(token);
        String username = claims.getSubject();
        return Response.ok("Hey, " + username + "! You are now connected to a secured endpoint.")
                .build();
        }

    /**
     * <p>getSecuredAdmin.</p>
     *
     * @param authHeader a {@link java.lang.String} object
     * @return a {@link jakarta.ws.rs.core.Response} object
     */
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
        return Response.ok("Hey, " + username + "! You are now connected to a secured endpoint as an ADMIN user.")
                .build();
        }
    }
