package main;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TeamFormation implements TeamBuilder{
    public static ArrayList<Team> teams = new ArrayList<>();
    public static ArrayList<Participant> participants = new ArrayList<>();

    public static int assignTeamSize(Scanner scanner){      //1.2(SD-define team size)
        while(true){
            System.out.print("Enter team size: ");

            try{
                int teamSize = Integer.parseInt(scanner.nextLine());

                if(teamSize<=2 || teamSize>=13){
                    System.out.println("Invalid team size. [Team size should be between 3 and 12]");    //1.3(SD-define team size)
                }else{
                    System.out.println("\nSuccessfully set team size to " + teamSize);      //1.4(SD-define team size)
                    return teamSize;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid team size!");
            }
        }
    }


    //GENERATING TEAMS ACCORDING TO THE MATCHING ALGORITHM
    @Override
    public void generateTeams(ArrayList<Participant> participants,int teamSize) {

        TeamFormation.participants = participants;
        //1.2(SD-generate teams)

        if (participants==null||participants.isEmpty()) {
            System.out.println("Please upload the participant details CSV first!");     //1.3(SD-generate teams)
            return;
        } else if (teamSize <=2 || teamSize >=13) {
            System.out.println("Please define the team size!");     //1.4(SD-generate teams)
            return;
        }
        teams.clear();

        //calculate number of teams
        int totalTeams = (int) Math.ceil((double) participants.size() / teamSize);
        for (int i = 0; i < totalTeams; i++)
            teams.add(new Team(teamSize));      //1.5(SD-generate teams)

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
        leaders.sort((a, b) -> Integer.compare(b.getSkillLevel(), a.getSkillLevel()));      //1.6(SD-generate teams)
        thinkers.sort((a, b) -> Integer.compare(b.getSkillLevel(), a.getSkillLevel()));     //1.7(SD-generate teams)
        balanced.sort((a, b) -> Integer.compare(b.getSkillLevel(), a.getSkillLevel()));     //1.8(SD-generate teams)


        //assign leaders (top -> bottom) and thinkers (bottom -> top)
        assignLeadersAndThinkers(leaders, thinkers, totalTeams);    //2.1(SD-generate teams)

        //assign balanced participants to weakest teams first
        assignBalancedParticipants(balanced, teamSize);         //2.2(SD-generate teams)

        //final balancing to ensure at least a leader and thinker per team
        finalBalanceTeams();    //2.3(SD-generate teams)

        //2.4(SD-generate teams)
        System.out.println("Total number of teams: " + teams.size());
        System.out.println("Total number of participants: " + participants.size());
        System.out.println("Leaders: " + leaders.size()+", Thinkers: " + thinkers.size()+", Balanced: " + balanced.size());
        System.out.println("\nTeams generated successfully.\n");
    }

    public void assignLeadersAndThinkers(ArrayList<Participant> leaders, ArrayList<Participant> thinkers, int totalTeams) {
        //leaders top -> bottom
        int teamIndex = 0;
        for (Participant p : leaders) {
            teams.get(teamIndex).addMember(p);  //2.1.1, 3.2.1(SD-generate teams)
            teamIndex=(teamIndex+1)%totalTeams;
        }

        //thinkers bottom -> top
        teamIndex=totalTeams-1;
        for (Participant p : thinkers) {    //2.1.2, 3.2.2 (SD-generate teams)
            teams.get(teamIndex).addMember(p);
            teamIndex--;
            if(teamIndex<0){
                teamIndex=totalTeams-1; //wrap around
            }
        }


    }

    public void assignBalancedParticipants(ArrayList<Participant> balanced, int teamSize) {     //2.2(SD-generate teams)
        for(Participant p : balanced){
            //find team with lowest average skill that is not full
            Team weakest=teams.stream().filter(t->t.members.size()<teamSize).min(Comparator.comparingDouble(Team::getAverageSkill)).orElse(null);   //2.2.1,  3.2.1(SD-generate teams)
            if(weakest!=null){
                weakest.addMember(p);   //2.2.2(SD-generate teams)
            }
        }
    }

    public void finalBalanceTeams() {   //2.3(SD-generate teams)
        for(Team t:teams){  //2.3.1(SD-generate teams)
            if(t.countRole("leader")==0){
                moveRoleToTeam("leader",t);     //2.3.2(SD-generate teams)
            }
            if(t.countRole("thinker")==0){  //2.3.3(SD-generate teams)
                moveRoleToTeam("thinker",t);    //2.3.4(SD-generate teams)
            }
        }
    }

    public void moveRoleToTeam(String role, Team target) {  //2.3.2, 2.3.4 (SD-generate teams)
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
    public void displayTeams(){     //1.2(SD-view teams)
        if (teams==null||teams.isEmpty()){
            System.out.println("No teams have been generated yet. Please generate teams first!");   //1.3(SD-view teams)
            return;
        }
        int count=1;
        for(Team t:teams){
            System.out.println("\n======= Team "+ count++ +"======");

            //number of participants
            int totalMembers=t.members.size();

            //count roles
            int leaders=t.countRole("leader");  //1.4(SD-view teams)
            int thinkers=t.countRole("thinker");    //1.5(SD-view teams)
            int balanced=t.countRole("balanced");   //1.6(SD-view teams)

            //count preferred games
            Map<String,Long> gameCount = t.members.stream().collect(Collectors.groupingBy(Participant::getPreferredGame,Collectors.counting()));

            //average skill
            double avgSkill=t.getAverageSkill();

            //display team info  1.7()SD-view teams
            System.out.println("Total participants: "+totalMembers);
            System.out.println("Leaders: "+leaders+", Thinkers: "+thinkers+", Balanced: "+balanced);
            System.out.println("Preferred games: "+gameCount);
            System.out.println("Average skill: "+avgSkill);
            System.out.println(t);
        }
    }



    //FIND WHICH TEAM A PARTICIPANT BELONGS TO
    public static Team TeamOfParticipant (String participantId){        //1.3(SD-view assigned team)
        if(teams==null){
            System.out.println("No teams have been created yet.");  //1.4(SD-view assigned team)
        }
        for(Team team:teams){       //1.5(SD-view assigned team)
            for(Participant p:team.members){        //1.6(SD-view assigned team)
                if(p.id.equals(participantId)){
                    return team;        //1.7(SD-view assigned team)
                }
            }
        }
        return null;
    }
}
