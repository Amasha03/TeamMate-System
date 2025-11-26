import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class TeamFormation implements TeamBuilder{
    public static ArrayList<Team> teams = new ArrayList<>();

    //GENERATING TEAMS ACCORDING TO THE MATCHING ALGORITHM
    @Override
    public void generateTeams(ArrayList<Participant> participants,int teamSize) {
        teams.clear();
        if (participants==null||participants.isEmpty()) {
            System.out.println("Please upload the participant details CSV first!");
            return;
        } else if (teamSize <=0) {
            System.out.println("Please define the team size!");
            return;
        }


        //calculate number of teams
        int totalTeams = (int) Math.ceil((double) participants.size() / teamSize);
        for (int i = 0; i < totalTeams; i++)
            teams.add(new Team(teamSize));

        //separate participants by personality
        ArrayList<Participant> leaders = new ArrayList<>();
        ArrayList<Participant> thinkers = new ArrayList<>();
        ArrayList<Participant> balanced = new ArrayList<>();

        for (Participant p : participants) {
            switch (p.personalityType.toLowerCase()) {
                case "leader":
                    leaders.add(p);
                    break;
                case "thinker":
                    thinkers.add(p);
                    break;
                case "balanced":
                    balanced.add(p);
                    break;
                default:
                    break;
            }
        }

        //sort participant list by skill descending
        leaders.sort((a, b) -> Integer.compare(b.getSkillLevel(), a.getSkillLevel()));
        thinkers.sort((a, b) -> Integer.compare(b.getSkillLevel(), a.getSkillLevel()));
        balanced.sort((a, b) -> Integer.compare(b.getSkillLevel(), a.getSkillLevel()));

        //assign leaders (top -> bottom) and thinkers (bottom -> top)
        assignLeadersAndThinkers(leaders, thinkers, totalTeams);

        //assign balanced participants to weakest teams first
        assignBalancedParticipants(balanced, teamSize);

        //final balancing to ensure at least a leader and thinker per team
        finalBalanceTeams();

        System.out.println("Total number of teams: " + teams.size());
        System.out.println("Total number of participants: " + participants.size());
        System.out.println("Leaders: " + leaders.size()+", Thinkers: " + thinkers.size()+", Balanced: " + balanced.size());
        System.out.println("\nTeams generated successfully.\n");
    }

    private void assignLeadersAndThinkers(ArrayList<Participant> leaders, ArrayList<Participant> thinkers, int totalTeams) {
        //leaders top -> bottom
        int teamIndex = 0;
        for (Participant p : leaders) {
            teams.get(teamIndex).addMember(p);
            teamIndex=(teamIndex+1)%totalTeams;
        }

        //thinkers bottom -> top
        teamIndex=totalTeams-1;
        for (Participant p : thinkers) {
            teams.get(teamIndex).addMember(p);
            teamIndex--;
            if(teamIndex<0){
                teamIndex=totalTeams-1; //wrap around
            }
        }

    }

    private void assignBalancedParticipants(ArrayList<Participant> balanced, int teamSize) {
        for(Participant p : balanced){
            //find team with lowest average skill that is not full
            Team weakest=teams.stream().filter(t->t.members.size()<teamSize).min(Comparator.comparingDouble(Team::getAverageSkill)).orElse(null);
            if(weakest!=null){
                weakest.addMember(p);
            }
        }
    }

    private void finalBalanceTeams() {
        for(Team t:teams){
            if(t.countRole("leader")==0){
                moveRoleToTeam("leader",t);
            }
            if(t.countRole("thinker")==0){
                moveRoleToTeam("thinker",t);
            }
        }
    }

    private void moveRoleToTeam(String role, Team target) {
        for(Team source : teams){
            if(source==target){
                continue;
            }
            for (Participant p:new ArrayList<>(source.members)){
                if(p.personalityType.equalsIgnoreCase(role)){
                    source.members.remove(p);
                    target.members.add(p);
                    return;
                }
            }
        }
    }

    //DISPLAY ALL GENERATED TEAMS
    @Override
    public void displayTeams(){
        int count=1;
        for(Team t:teams){
            System.out.println("\n======= Team "+ count++ +"======");

            //number of participants
            int totalMembers=t.members.size();

            //count roles
            int leaders=t.countRole("leader");
            int thinkers=t.countRole("thinker");
            int balanced=t.countRole("balanced");

            //count preferred games
            Map<String,Long> gameCount = t.members.stream().collect(Collectors.groupingBy(Participant::getPreferredGame,Collectors.counting()));

            //average skill
            double avgSkill=t.getAverageSkill();

            //display team info
            System.out.println("Total participants: "+totalMembers);
            System.out.println("Leaders: "+leaders+", Thinkers: "+thinkers+", Balanced: "+balanced);
            System.out.println("Preferred games: "+gameCount);
            System.out.println("Average skill: "+avgSkill);
            System.out.println(t);
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
