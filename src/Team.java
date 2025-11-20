import java.util.*;
public class Team {
    String teamID;
    int teamSize;
    ArrayList<Participant> members=new ArrayList<>();


    public Team(int teamSize) {
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
