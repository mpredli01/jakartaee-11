package org.redlich.data;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("data")
public class DataResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    DataService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String data() {

        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n");
        builder.append(service.message());
        builder.append("\n");
        return builder.toString();
        }
    }
