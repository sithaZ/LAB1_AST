package Lab01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserCreationAndGetUsername() {
        // Arrange & Act
        User user = new User("Sitha");
        
        // Assert
        assertEquals("Sitha", user.getUsername(), "The username should match what was passed to the constructor.");
    }

    @Test
    public void testSetUsername() {
        // Arrange
        User user = new User("OldName");
        
        // Act
        user.setUsername("NewName");
        
        // Assert
        assertEquals("NewName", user.getUsername(), "The username should update after calling setUsername.");
    }
}