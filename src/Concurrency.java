import java.util.*;
import java.util.concurrent.*;

public class Concurrency {
    private ArrayList<Participant> participants;

    public Concurrency(ArrayList<Participant> participants){
        this.participants = participants;
    }

    //process survey concurrently
    public void processSurveys(){
        if (participants==null||participants.isEmpty()){
            return;
        }
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for(Participant p : participants){
            executor.submit(() ->{
                Survey survey = p.survey;
                if(survey != null){
                    int score = 0;
                    for(int s: survey.surveyScores){
                        score += s;
                    }
                    survey.personalityScore = score*4;

                    if(survey.personalityScore > 90){
                        survey.personalityType="Leader";
                    } else if(survey.personalityScore > 70) {
                        survey.personalityType="Balanced";
                    } else if (survey.personalityScore>50) {
                        survey.personalityType="Thinker";
                    }else{
                        survey.personalityType="Unknown";
                    }
                    p.personalityType=survey.personalityType;
                    p.surveyCompleted=true;
                }
            });
        }
        executor.shutdown();
        try{
            executor.awaitTermination(10,TimeUnit.MINUTES);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Generate teams concurrently
    public ArrayList<Team> formTeams(int teamSize){
        ArrayList<Team> teams = new ArrayList<>();
        if(participants==null||participants.isEmpty()){
            return teams;
        }
        Collections.shuffle(participants);
        int totalTeams=(int) Math.ceil((double)participants.size()/teamSize);
        for(int i=0;i<totalTeams;i++){
            teams.add(new Team(teamSize));
        }

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i=0; i<participants.size();i++){
            final int idx = i;
            executor.submit(()->{
                synchronized(teams){
                    teams.get(idx%teams.size()).addMember(participants.get(idx));
                }
            });
        }
        executor.shutdown();
        try{
            executor.awaitTermination(10,TimeUnit.MINUTES);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return teams;
    }



}
