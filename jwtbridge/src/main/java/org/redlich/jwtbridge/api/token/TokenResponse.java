package org.redlich.jwtbridge.api.token;

/**
 * <p>TokenResponse class.</p>
 *
 * @author mpredli01
 */
public class TokenResponse {
    private String token;

    /**
     * <p>Default constructor.</p>
     */
    public TokenResponse() {
        }

    /**
     * <p>Getter for the field <code>token</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getToken() {
        return token;
        }

    /**
     * <p>Setter for the field <code>token</code>.</p>
     *
     * @param token a {@link java.lang.String} object
     */
    public void setToken(String token) {
        this.token = token;
        }

    /**
     * <p>displayToken.</p>
     *
     * @param token a {@link java.lang.String} object
     */
    public void displayToken(String token) {
        System.out.println("-----> " + token);
        }
    }
