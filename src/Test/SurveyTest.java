package Test;
import main.Survey;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SurveyTest {

    @Test
    public void testSurvey() {
        Survey survey = new Survey("Valorant","Attacker",85,"Leader",8);

        assertEquals("Valorant",survey.getInterests());
        assertEquals("Attacker", survey.getPreferredRole());
        assertEquals(8,survey.getSkillLevel());
        assertEquals(85,survey.personalityScore);
        assertEquals("Leader",survey.personalityType);
    }
}
