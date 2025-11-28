package Test;
import main.Participant;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ParticipantTest {
    public Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant("P001", "John Doe", "john@university.edu");
    }

    @Test
    public void testParticipantCreation() {
        assertEquals("P001", participant.getId());
        assertEquals("John Doe", participant.getName());
        assertEquals("john@university.edu", participant.getEmail());
        assertFalse(participant.surveyCompleted);
    }

    @Test
    public void testSettersAndGetters() {
        participant.setPreferredGame("Valorant");
        participant.setSkillLevel(8);
        participant.setPreferredRole("Attacker");
        participant.setPersonalityScore(85);
        participant.setPersonalityType("Leader");

        assertEquals("Valorant", participant.getPreferredGame());
        assertEquals(8, participant.getSkillLevel());
        assertEquals("Attacker", participant.getPreferredRole());
        assertEquals(85, participant.getPersonalityScore());
        assertEquals("Leader", participant.getPersonalityType());
    }
}
