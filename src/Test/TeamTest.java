package Test;
import main.Participant;
import main.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class TeamTest {
    private Team team;
    private Participant participant;

    @BeforeEach
    public void setUp() {
        team=new Team(3);
        participant=new Participant("P201","Participant_201", "user201@university.edu");
        participant.setSkillLevel(8);
    }

    @Test
    void testAddMember() {
        Assertions.assertTrue(team.addMember(participant));
        assertEquals(1,team.members.size());
    }

    @Test
    void testAddMemberWhenFull(){
        team.addMember(participant);
        team.addMember(new Participant("P202","Participant_202","user202@university.edu"));
        team.addMember(new Participant("P203","Participant_203","user203@university.edu"));


        assertFalse(team.addMember(new Participant("P204","Participant_204","user204@university@edu")));

    }

    @Test
    void testAverageSkill(){
        Participant p2=new Participant("P202","Participant_202","user202@university.edu");
        p2.setSkillLevel(8);
        team.addMember(p2);


        Assertions.assertEquals(8, team.getAverageSkill());
    }
}
