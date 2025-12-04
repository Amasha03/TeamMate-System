package main;
import java.util.*;
public class Team {
    String teamID;
    int teamSize;
    public ArrayList<Participant> members=new ArrayList<>();


    public Team(int teamSize) {     //1.5(SD-generate teams)
        this.teamID=teamID;
        this.teamSize=teamSize;
    }

    //ADD PARTICIPANTS ACCORDING TO THE TEAM SIZE
    public boolean addMember(Participant p){
        if(members.size() >= teamSize )
            return false;
        members.add(p);
        return true;
    }

    public double getAverageSkill() {
        if (members.isEmpty()) return 0;
        int total = 0;
        for (Participant p : members) {
            total += p.getSkillLevel();
        }
        return (double) total / members.size();
    }

    public int countRole(String role) {  //1.5 , 1.6 , 1.7 (SD-view teams)
        int count = 0;
        for (Participant p : members) {
            if (p.personalityType.equalsIgnoreCase(role)) count++;
        }
        return count;   //1.5.1,  1..1,  1.6.1 (SD-view teams)
    }

    //Team Object
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Members: \n");
        for(Participant p:members)
            sb.append(" - ").append(p.getTeamDisplayInfo()).append("\n");
        return sb.toString();
    }
}
