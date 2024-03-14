package org.redlich.data;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * <p>ConstraintViolationExceptionMapper class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Provider
@ApplicationScoped
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    /** {@inheritDoc} */
    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(extractEntity(e))
                .build();
        }

    private Object extractEntity(ConstraintViolationException constraintViolationException) {
        JsonArrayBuilder messages = Json.createArrayBuilder();
        for (ConstraintViolation<?> v : constraintViolationException.getConstraintViolations()) {
            messages.add(v.getMessage());
            }
        return messages.toString();
        }
    }
