package org.redlich.security;

import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.io.Serial;

/**
 * Test Servlet that prints out the name of the authenticated caller and whether
 * this caller is in any of the roles {foo, bar, kaz}
 *
 * @author mpredli01
 */
@WebServlet("/protectedServlet1")
@ServletSecurity(@HttpConstraint(rolesAllowed = "foo"))
@DeclareRoles({"bar", "kaz"})
public class ProtectedServlet1 extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Inject the configuration property, message, from the microprofile-config.properties file.
     */
    @Inject
    @ConfigProperty(name = "message")
    String message;

    /**
     * Inject an instance of {@link SecurityService} class.
     */
    @Inject
    SecurityService securityService;

    /**
     * Inject an instance of the {@link SecurityContext} interface.
     */
    @Inject
    private SecurityContext securityContext;

    /**
     * <p>Default constructor.</p>
     */
    public ProtectedServlet1() {
        }

    /** {@inheritDoc} */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String webName = null;
        if (request.getUserPrincipal() != null) {
            webName = request.getUserPrincipal().getName();
            }
        response.getWriter().write(message + "\n\n");
        response.getWriter().write("Web username: " + webName + "\n");
        response.getWriter().write("* user has role \"foo\": " + request.isUserInRole("foo") + "\n");
        response.getWriter().write("* user has role \"bar\": " + request.isUserInRole("bar") + "\n");
        response.getWriter().write("* user has role \"kaz\": " + request.isUserInRole("kaz") + "\n\n");

        String contextName = null;
        if (securityContext.getCallerPrincipal() != null) {
            contextName = securityContext.getCallerPrincipal().getName();
            }

        response.getWriter().write("Context username: " + contextName + "\n");
        response.getWriter().write("* user has role \"foo\": " + securityContext.isCallerInRole("foo") + "\n");
        response.getWriter().write("* context user has role \"bar\": " + securityContext.isCallerInRole("bar") + "\n");
        response.getWriter().write("* context user has role \"kaz\": " + securityContext.isCallerInRole("kaz") + "\n");
        response.getWriter().write("* user has has access to web resource: " + securityContext.hasAccessToWebResource("/servlets") + "\n\n");
        response.getWriter().write(securityService.message());
        }
    }
