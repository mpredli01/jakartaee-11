package org.redlich.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;


/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("rest")
@Produces(MediaType.APPLICATION_JSON)
public class RestApplication extends Application {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @GET
    public String sayHello() {
        return this.message;
        }
    }
