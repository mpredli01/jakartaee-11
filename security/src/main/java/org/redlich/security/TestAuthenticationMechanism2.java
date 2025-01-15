package org.redlich.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;

@ApplicationScoped
public class TestAuthenticationMechanism2 implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {

        // Get the (caller) name and password from the request
        // NOTE: This is for the smallest possible example only. In practice
        // putting the password in a request query parameter is highly
        // insecure
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name != null && password != null) {

            // Delegate the {credentials in -> identity data out} function to
            // the Identity Store
            CredentialValidationResult result = identityStoreHandler.validate(
                    new UsernamePasswordCredential(name, password));

            if (result.getStatus() == VALID) {
                // Communicate the details of the authenticated user to the
                // container. In many cases the underlying handler will just store the details
                // and the container will actually handle the login after we return from
                // this method.
                return httpMessageContext.notifyContainerAboutLogin(
                        result.getCallerPrincipal(), result.getCallerGroups());
                }
            else {
                return httpMessageContext.responseUnauthorized();
                }
            }

        return httpMessageContext.doNothing();
        }
    }
