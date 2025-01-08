package org.redlich.jwtbridge.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("unsecured")
public class UnsecuredResource {

    @GET
    @Path("/hi-without-token")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUnsecuredHello() {
        return "Hey! This is an unsecured endpoint.";
        }
    }
