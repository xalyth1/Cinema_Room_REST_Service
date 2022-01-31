package cinema;

import java.util.UUID;

/**
 * Object of this class is used to deserialize request body JSON and get UUID
 *
 */
public class Token {
    private UUID token;

    public Token(UUID token) {
        this.token = token;
    }

    public Token() {

    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
