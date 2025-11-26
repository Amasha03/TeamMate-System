import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class Concurrency {
    private ArrayList<Participant> participants;

    private final ExecutorService executor = Executors.newFixedThreadPool(5);


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
                try{
                    Survey survey = p.survey;

                    if(survey != null) {
                        int score = 0;

                        for (int s : survey.surveyScores) {
                            score += s;
                        }

                        survey.personalityScore = score * 4;

                        if (survey.personalityScore > 90) {
                            survey.personalityType = "Leader";
                        } else if (survey.personalityScore > 70) {
                            survey.personalityType = "Balanced";
                        } else if (survey.personalityScore > 50) {
                            survey.personalityType = "Thinker";
                        } else if(survey.personalityScore > 0) {
                            survey.personalityType = "Unknown";
                        }else{
                            System.out.println("No survey found for participant "+p.getId());
                        }
                        p.personalityType = survey.personalityType;
                        p.surveyCompleted = true;
                    }
                }catch (Exception e) {
                    System.out.println("Error in processing Survey!"+e.getMessage());
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

    private final TeamFormation teamFormation = new TeamFormation();

    //Generate teams concurrently
    public void formTeams(ArrayList<Participant> participants,int teamSize) {

        if (participants==null||participants.isEmpty()){
            System.out.println("No participants available.");
            return;
        }
        try{
            //create teams(threads)
            Future<?> createTeamsFuture = executor.submit(() -> teamFormation.generateTeams(participants,teamSize));

            //wait for teams to be created
            createTeamsFuture.get();

        }catch(Exception e){
            System.out.println("Error during concurrent team formation.");
            e.printStackTrace();
        }
    }
    public void shutdown(){
        executor.shutdown();
    }



}
