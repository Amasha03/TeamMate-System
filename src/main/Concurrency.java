package main;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class Concurrency {
    private ArrayList<Participant> allparticipants;
    private TeamFormation teamFormation=new TeamFormation();
    private final ExecutorService surveyExecutor;


    public Concurrency(ArrayList<Participant> participants){
        this.allparticipants = participants;
        int numThreads=Math.max(1,Math.min(participants.size(),10));
        this.surveyExecutor=Executors.newFixedThreadPool(numThreads);
    }



    //process surveys using threads
    public void processSurveys(){
        if (allparticipants==null||allparticipants.isEmpty()){
            return;
        }

        List <Callable<Void>> tasks = new ArrayList<>();

        for(Participant p : allparticipants) {
            tasks.add(() -> {
                try {
                    Survey survey = p.survey;

                    if (survey != null) {
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
                        } else if (survey.personalityScore > 0) {
                            survey.personalityType = "Unknown";
                        } else {
                            System.out.println("No survey found for participant " + p.getId());
                        }
                        p.personalityType = survey.personalityType;
                        p.surveyCompleted = true;
                    }
                } catch (Exception e) {
                    System.out.println("Error in processing Survey!" + e.getMessage());
                }
                return null;
            });
        }
        try{
            surveyExecutor.invokeAll(tasks);
        } catch (InterruptedException e) {
            System.out.println("Survey processing interrupted: " + e.getMessage());
        }
        /**
        threads.add(t);
        t.start();
        }
        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException ignored) {

            }
        }**/

    }


    //Generate teams concurrently
    public void formTeams(int teamSize) {       //1.2(SD-generate teams )

        if (allparticipants==null||allparticipants.isEmpty()){
            System.out.println("No participants available.");
            return;
        }

        ArrayList<Participant> leaders=new ArrayList<>();
        ArrayList<Participant> thinkers=new ArrayList<>();
        ArrayList<Participant> balanced=new ArrayList<>();

        for (Participant p : allparticipants){
            switch (p.personalityType.toLowerCase()){
                case "leader":
                    leaders.add(p);
                    break;
                case  "balanced":
                    balanced.add(p);
                    break;
                case "thinker":
                    thinkers.add(p);
                    break;
            }
        }

        int totalTeams=(int)Math.ceil((double) allparticipants.size()/teamSize);

        TeamFormation.teams.clear();
        for (int i = 0; i < totalTeams; i++) {
            TeamFormation.teams.add(new Team(teamSize));
        }

        //thread1
        Thread t1=new Thread(() ->{     //3.1(SD-generate teams)
            teamFormation.assignLeadersAndThinkers(leaders,thinkers,totalTeams);    //3.2(SD-generate teams)
        });

        //thread2
        Thread t2=new Thread(() ->{
            teamFormation.assignBalancedParticipants(balanced, teamSize);
        });

        //start threads
        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            System.out.println("Error while running parallel tasks!"+e.getMessage());
        }

        //final balancing
        teamFormation.finalBalanceTeams();

        System.out.println("Team formation completed!");
    }
    public void shutdown(){
        surveyExecutor.shutdown();
        try{
            if(!surveyExecutor.awaitTermination(10,TimeUnit.SECONDS)){
                surveyExecutor.shutdownNow();
            }
        }catch (InterruptedException e){
            surveyExecutor.shutdownNow();
        }
    }



}
