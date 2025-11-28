package Test;
import main.Participant;
import main.Team;
import main.TeamFormation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TeamFormationTest {
    private TeamFormation teamFormation;
    private ArrayList<Participant> participants;

    @BeforeEach
    public void setUp() {
        teamFormation = new TeamFormation();
        participants = new ArrayList<>();

        //test participants
        Participant p1=new Participant("P201","Participant_201","user201@university.edu",true,"Leader");
        p1.setSkillLevel(9);
        p1.setPreferredGame("Valorant");

        Participant p2=new Participant("P202","Participant_202","user202@university.edu",true,"Thinker");
        p2.setSkillLevel(7);
        p2.setPreferredGame("Valorant");

        participants.add(p1);
        participants.add(p2);
    }

    @Test
    void testGenerateTeams(){
        teamFormation.generateTeams(participants,2);
        assertFalse(TeamFormation.teams.isEmpty());
    }

    @Test
    void testGenerateParticipants(){
        teamFormation.generateTeams(new ArrayList<>(),3);
    }

    @Test
    void testTeamOfParticipant(){
        teamFormation.generateTeams(participants,2);
        Team team=TeamFormation.TeamOfParticipant("P201");
    }


}
