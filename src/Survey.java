import java.util.*;

public class Survey {
    int[] personalityScores=new int[5];
    String interests;
    String preferredRole;
    Survey survey;

    //constructor
    public Survey(int[] scores,String interests,String preferredRole){
        this.personalityScores=scores;
        this.interests=interests;
        this.preferredRole=preferredRole;
    }

    //calculate personality score
    public int calPersonalityScore(){
        int total=0;
        int totalScore=0;
        for(int i=0;i<personalityScores.length;i++){
            total+=personalityScores[i];
            totalScore=total*4;
        }
        return totalScore;
    }

    //classify personality
    public String classifyPersonality(){
        int score=calPersonalityScore();
        if(score>=90)
            return "Leader";
        else if(score>=70)
            return "Balanced";
        else if (score>=50)
            return "Thinker";
        else
            return "unknown";
    }

    //Getters
    public String getInterests(){
        return interests;
    }
    public String getPreferredRole(){
        return preferredRole;
    }
    
    public void setSurvey(Survey survey){
        this.survey=survey;
    }

    @Override
    public String toString(){
        return "Personality Type: "+classifyPersonality()+"\n Preferred Role: "+getPreferredRole()+"\n Interests: "+getInterests();
    }
    
}
