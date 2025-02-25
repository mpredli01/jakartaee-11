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

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to define a JSON Web Token (JWT) authentication mechanism.
 * JWT authentication mechanism can verify JWT bearer access tokens which are sent with HTTP Authorization or other headers.
 *
 * @author mpredli01
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface JwtAuthenticationMechanismDefinition {

    /**
     * Required, unless {@link #publicKeyLocation()} is specified.
     *
     * @return Base64 or Base64URL encoded public verification key in PEM, JSON Web Key (JWK) or JSON Web Key Set (JWKS) format.
     */
    String publicKey() default "";
    /**
     * Required, unless {@link #publicKey()} is specified.
     *
     * @return Location of the public verification key in PEM, JSON Web Key (JWK) or JSON Web Key Set (JWKS) format.
     * The key can be located on the file system, classpath or at the remote HTTP(S) endpoint.
     */
    String publicKeyLocation() default "";

    /**
     * Required, with the "RS256" algorithm used by default.
     *
     * @return Public key algorithms that can be used to verify the token signature. "RS256" or "ES256" algorithms must
     *         be supported.
     */
    String[] publicKeyAlgorithm() default {"RS256"};

    /**
     * Required if the inner-signed JWT tokens must be decrypted.
     *
     * @return Location of the private or secret decryption key.
     */
    String decryptKeyLocation() default "";

    /**
     * Required if the inner-signed JWT tokens must be decrypted, with the "RSA-OAEP-256" algorithm used by default.
     *
     * @return decryption key algorithms that can be used to decrypt the token. "RSA-OAEP-256" or "RSA-OAEP" algorithms
     *         must be supported.
     */
    String[] decryptKeyAlgorithm() default {"RSA-OAEP-256"};

    /**
     * Required.
     *
     * @return Token issuer
     */
    String issuer();

    /**
     * Recommended.
     *
     * @return Token audiences
     */
    String[] audiences() default {};

    /**
     * Required.
     *
     * @return Name of the HTTP header which is used to pass JWT tokens.
     * The defaut HTTP header name is "Authorization".
     * If an HTTP header name is "Authorization" then its "Bearer" authorization scheme must be used to pass JWT tokens.
     */
    String header() default "Authorization";

    /**
     * Required if the {@link #header()} property is set to "Cookie".
     * The default cookie name is "Bearer".
     *
     * @return Name of the cookie whose value is a JWT token.
     */
    String cookieName() default "Bearer";

    /**
     * Recommended.
     *
     * @return Token age. It is undefined if it is set to -1 which is a default value.
     */
    long tokenAge() default -1;

    /**
     * Recommended when minor clock skews, typically up to 60 seconds, should be taken into consideration
     * during the token expiration check.
     *
     * @return Clock skew. It is undefined if it is set to -1 which is a default value.
     */
    long clockSkew() default -1;
    }
