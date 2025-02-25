package org.redlich.jwtbridge.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.redlich.jwtbridge.JwtBridgeService;

/**
 * Configures RESTful Web Services for the application.
 *
 * @author mpredli01
 */
@ApplicationPath("/jwtbridge")
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class JwtBridgeApplication extends Application {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    JwtBridgeService jwtBridgeService;

    /**
     * <p>sayHello.</p>
     *
     * @return a {@link java.lang.String} object
     */
    @GET
    public String sayHello() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n\n");
        builder.append(jwtBridgeService.message());
        return builder.toString();
        }
    }
