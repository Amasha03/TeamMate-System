import java.util.*;
import java.util.concurrent.*;

public class Concurrency {
    private ArrayList<Participant> participants;

    public Concurrency(ArrayList<Participant> participants){
        this.participants = participants;
    }

    //process surveys using threads
    public void processSurveys(){
        if (participants==null||participants.isEmpty()){
            return;
        }

        ArrayList <Thread> threads = new ArrayList<>();

        for(Participant p : participants){
            Thread t=new Thread(() ->{
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
        threads.add(t);
        t.start();
        }
        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //Generate teams concurrently
    public ArrayList<Team> formTeams(int teamSize) {
        ArrayList<Team> teams = new ArrayList<>();

        if (participants == null || participants.isEmpty()) {
            return teams;
        }
        Collections.shuffle(participants);

        int totalTeams = (int) Math.ceil((double) participants.size() / teamSize);
        for (int i = 0; i < totalTeams; i++) {
            teams.add(new Team(teamSize));
        }

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < participants.size(); i++) {
            final int idx = i;

            Thread t = new Thread(() -> {
                synchronized (teams) {
                    teams.get(idx % teams.size()).addMember(participants.get(idx));
                }
            });

            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return teams;
    }



}
