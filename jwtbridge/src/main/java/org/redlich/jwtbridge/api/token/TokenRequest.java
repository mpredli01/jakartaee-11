package org.redlich.jwtbridge.api.token;

/**
 * <p>TokenRequest class.</p>
 *
 * @author mpredli01
 */
public class TokenRequest {
    private String username;
    private String password;

    /**
     * <p>Getter for the field <code>username</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getUsername() {
        return username;
        }

    /**
     * <p>Setter for the field <code>username</code>.</p>
     *
     * @param username a {@link java.lang.String} object
     */
    public void setUsername(String username) {
        this.username = username;
        }

    /**
     * <p>Getter for the field <code>password</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getPassword() {
        return password;
        }

    /**
     * <p>Setter for the field <code>password</code>.</p>
     *
     * @param password a {@link java.lang.String} object
     */
    public void setPassword(String password) {
        this.password = password;
        }
    }
