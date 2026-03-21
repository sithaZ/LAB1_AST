package Lab01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.concurrent.TimeUnit;

public class AuthenticationTest {
    private Authentication auth;

    @BeforeEach
    void setUp() {
        auth = new Authentication();
    }

    @Test
    void testLoginSuccess() throws Exception {
        // Ensure NestJS is running with 'admin'/'1234'
        Boolean result = auth.login("admin", "1234").get(5, TimeUnit.SECONDS);
        assertTrue(result, "Login should succeed for valid credentials");
    }

    @Test
    void testLoginFailure() throws Exception {
        Boolean result = auth.login("wrongUser", "wrongPass").get(5, TimeUnit.SECONDS);
        assertFalse(result, "Login should fail for invalid credentials");
    }
}