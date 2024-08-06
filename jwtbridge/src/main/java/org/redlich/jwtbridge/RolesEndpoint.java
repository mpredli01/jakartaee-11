/*
 * Copyright (c) 2023-2024 Contributors to the Eclipse Foundation
 *
 *  See the NOTICE file(s) distributed with this work for additional
 *  information regarding copyright ownership.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.redlich.jwtbridge;

import java.security.Principal;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Path("/endp")
@DenyAll
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class RolesEndpoint {

    /**
     *
     * @param securityContext the security context
     * @param input the value added to the query string
     * @return the resulting String upon successful login
     */
    @GET
    @Path("/echo")
    @RolesAllowed("Echoer")
    /*
    @JwtAuthenticationMechanismDefinition(
            publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAtL6HShqY5H4y56rsCo7V\n" +
                    "dhT9/eLQwsJpKWg66j98XsB/qc5ZxkJ25GXCzpjR0ZvzAxMNlj1hrMORaKVzz2/5\n" +
                    "axZgF1eZfzgrNyQ9rtGaBtMNAB20jLsoYp5psRTaYxKeOiLHPr3956ukSRUF9YfJ\n" +
                    "GSamrvGOwC8h6zbq6uaydv+FVJXijlMD/iCggUfoirtVOWK/X1IzV7covxcGzT0X\n" +
                    "019/4RbtjLdnvqZnGqmpHQpBEItI+4gNvaKR8NDWUxAjO/v+oOKR5nEUnDWcQSCx\n" +
                    "KmyQrVJtHr9PBwWrHzTSx4k1L1hLf+AWXAdy/r6c0Lzgt5knmZTyWDG2+n8SlrXx\n" +
                    "HHxFO1Wz8H/OKBzTAf8zIuj2lkXYo+M6aoJM7qQmTys80dtYvnaHGSl+jpe2plMb\n" +
                    "S9RS4XcHH7vCqJc9acBnp9CvLgjOmA0b5Rc0WyN4sn1SDFYe6HZcVo4YGTbtTTlw\n" +
                    "gu/ozQ1x+xpTAaU0mWkHMwT0CO79rPORjhDXokEuduvtp6VUiAaoFF6Y3QQLf6O3\n" +
                    "P9p8yghpBBLb460lEQqOHQQGP0EK46cU81dlcD5lYE0TayDzb9pZZWUyjIE4Elzy\n" +
                    "W7wgI4xw7czdBalN+IhXKfGUCqIDVh7X7JpmskZMaRixf424yBcZLntEejZy59yL\n" +
                    "DSssHMc/bqnBraXuo8JBEPkCAwEAAQ==",
            publicKeyLocation = "/publicKey.pem",
            publicKeyAlgorithm = "ES256",
            decryptKeyLocation = "",
            decryptKeyAlgorithm = "RS256",
            issuer = "https://server.example.com",
            audiences = "s6BhdRkqt3,testing",
            header = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9",
            cookieName = "",
            tokenAge = 10,
            clockSkew = 10
            )
     */
    public String echoInput(@Context SecurityContext securityContext, @QueryParam("input") String input) {
        Principal user = securityContext.getUserPrincipal();
        return "\n" + "Login successful!" + "\n" + "input = " + input + ", user = " + user.getName() + "\n";
        }
    }
