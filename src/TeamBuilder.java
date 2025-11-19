import java.util.ArrayList;

public interface TeamBuilder {
    void generateTeams(ArrayList<Participant> members, int teamSize);
    void displayTeams();
    void saveTeamsToCSV(String filePath);

}
