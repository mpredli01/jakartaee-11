package org.redlich.jwtbridge.api.token;

import org.redlich.jwtbridge.users.User;
import org.redlich.jwtbridge.users.UserFacade;
import org.redlich.jwtbridge.util.BuildScript;
import org.redlich.jwtbridge.util.JWTUtils;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * <p>TokenResource class.</p>
 *
 * @author mpredli01
 */
@Path("/token")
public class TokenResource {

    @Inject
    UserFacade userFacade;

    /**
     * <p>Default constructor.</p>
     */
    public TokenResource() {
        }

    /**
     * <p>generateToken.</p>
     *
     * @param request a {@link org.redlich.jwtbridge.api.token.TokenRequest} object
     * @return a {@link jakarta.ws.rs.core.Response} object
     */
    @POST
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateToken(TokenRequest request) {
        try {
            // Validate credentials (optional)
            if (request == null || !isValidUser(request.getUsername(), request.getPassword())) {
                throw new UnauthorizedException("Invalid credentials");
                }

            // Generate JWT token
            final User user = userFacade.getUser(request.getUsername());
            String token = JWTUtils.generateToken(request.getUsername(), user.getRoles());

            TokenResponse response = new TokenResponse();
            response.setToken(token);
            BuildScript script = new BuildScript(token);
            script.writeToFile(token);

            // Return success response with the token
            return Response.ok(response).build();
            }
        catch (UnauthorizedException e) {
            // Return 401 status for invalid credentials
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("HTTP 401: Unauthorized (invalid credentials)")
                    .build();
            }
        }

    private boolean isValidUser(String username, String password) {
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            return userFacade.verifyUserPassword(username, password);
            }
        return false;
        }

    /**
     * <p>UnauthorizedException class.</p>
     */
    public static class UnauthorizedException extends RuntimeException {
        /**
         * <p>UnauthorizedException.</p>
         * @param message the message to display.
         */
        public UnauthorizedException(String message) {
            super(message);
            }
        }
     }
