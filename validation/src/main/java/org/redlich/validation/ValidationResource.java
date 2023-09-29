package org.redlich.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Set;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("validate")
public class ValidationResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    ValidationService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String validate() {
        User user = new User(null, true, "Java Champion", 13, "mike");

        StringBuilder builder = new StringBuilder();
        builder.append(this.message);
        builder.append("\n");
        builder.append(user);
        builder.append("\n");

        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            for (ConstraintViolation<User> violation : violations) {
                builder.append(violation.getMessage());
                builder.append("\n");
                }
            builder.append(service.message());
            builder.append("\n");
            }
        return builder.toString();
        }
    }
