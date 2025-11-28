package Test;
import main.Participant;
import main.Survey;
import main.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {
    private final Participant testUser=new Participant("P201","Participant_201","user201@university.edu");

    @Test
    void testValidId(){
        Assertions.assertTrue(testUser.isValidId("P201"));
        Assertions.assertTrue(testUser.isValidId("User201"));
        Assertions.assertFalse(testUser.isValidId(""));
        Assertions.assertFalse(testUser.isValidId(null));
    }

    @Test
    void testValidEmail(){
        Assertions.assertTrue(testUser.isValidEmail("user201@university.edu"));
        Assertions.assertFalse(testUser.isValidEmail("invalid-email"));
        Assertions.assertFalse(testUser.isValidEmail(""));
        Assertions.assertFalse(testUser.isValidEmail(null));
    }
}
