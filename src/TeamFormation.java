import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TeamFormation implements TeamBuilder{
    private static ArrayList<Team> teams = new ArrayList<>();

    //GENERATING TEAMS ACCORDING TO THE MATCHING ALGORITHM
    @Override
    public void generateTeams(ArrayList<Participant> participants,int teamSize){
        teams.clear();
        if(participants.isEmpty() || teamSize <= 0){
            System.out.println("Participants list empty or invalid team size.");
            return;
        }

        //Shuffle participants to randomize
        Collections.shuffle(participants);

        //calculate number of teams
        int totalTeams = (int) Math.ceil((double) participants.size() / teamSize);
        for(int i=0;i<totalTeams;i++)
            teams.add(new Team(teamSize));

        //separate participants by personality
        ArrayList<Participant>leaders=new ArrayList<>();
        ArrayList<Participant> thinkers=new ArrayList<>();
        ArrayList<Participant> balanced=new ArrayList<>();

        for(Participant p:participants){
            switch(p.personalityType.toLowerCase()){
                case "leader":leaders.add(p);
                    break;
                case "thinker":thinkers.add(p);
                    break;
                case "balanced":balanced.add(p);
                    break;
                default:
                    break;
            }
        }

        //Add one leader per team
        int teamIndex=0;
        for(Participant p:leaders){
            teams.get(teamIndex % totalTeams).addMember(p);
            teamIndex++;
        }

        //Add thinkers
        for(Participant p:thinkers){
            teams.get(teamIndex % totalTeams).addMember(p);
            teamIndex++;
        }

        //Add remaining balanced
        for(Participant p:balanced){
            teams.get(teamIndex % totalTeams).addMember(p);
            teamIndex++;
        }
        System.out.println("Teams generated successfully.");
    }

    //DISPLAY ALL GENERATED TEAMS
    @Override
    public void displayTeams(){
        int t=1;
        for(Team team:teams){
            System.out.println("\n======= Team "+ t++ +"======");
            System.out.println(team);
        }
    }

    //SAVE GENERATED TEAMS TO A CSV FILE
    @Override
    public void saveTeamsToCSV(String filePath){

        String teamsFilePath="resources/formed_teams.csv";

        try(FileWriter fw = new FileWriter(teamsFilePath)){
            fw.write("Team Id,Participant Id,Participant Name,Preferred Game,Preferred Role,Personality Type,Skill Level\n");
            int teamId=1;
            for(Team team:teams){
                for(Participant participant: team.members){
                    fw.write(teamId+","+participant.id+","+participant.getName()+","+participant.getPreferredGame()+","+participant.getPreferredRole()+","+participant.getPersonalityType()+","+participant.getSkillLevel()+"\n");

                }
                teamId++;
                fw.write("\n");
            }
            System.out.println("Teams successfully saved to  "+teamsFilePath);
        }catch (IOException e){
            System.out.println("Error occurred in saving CSV: "+e.getMessage());
        }
    }

    //FIND WHICH TEAM A PARTICIPANT BELONGS TO
    public static Team TeamOfParticipant (String participantId){
        for(Team team:teams){
            for(Participant p:team.members){
                if(p.id.equals(participantId)){
                    return team;
                }
            }
        }
        return null;
    }
}
