import java.util.ArrayList;

public interface TeamBuilder {

    //GENERATE TEAMS FROM ALL PARTICIPANTS
    void generateTeams(ArrayList<Participant> members, int teamSize);

    //DISPLAY ALL GENERATED TEAMS
    void displayTeams();

    //SAVE ALL TEAMS TO A CSV FILE
    //void saveTeamsToCSV(String filePath);

}
