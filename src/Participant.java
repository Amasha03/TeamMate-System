public class Participant {
    private String participantID;
    private String name;
    private String email;
    public boolean surveyCompleted;
    public Survey survey;
    private String personalityType;

    //constructor
    public Participant(String participantID, String name, String email,boolean surveyCompleted,Survey survey){
        this.participantID=participantID;
        this.name=name;
        this.email=email;
        this.surveyCompleted=surveyCompleted;
        this.survey=survey;
    }

    public void completeSurvey(Survey survey){
        this.survey=survey;
        this.surveyCompleted=true;
        this.personalityType=survey.classifyPersonality();
    }


    //getters
    public String getparticipantID(){
        return participantID;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public Survey getSurvey(){
        return survey;
    }

    @Override
    public String toString(){
        return "ID: "+participantID+"\nName: "+name+"\nEmail: "+email+"\nSurvey Completed: "+surveyCompleted+"\nSurvey:\n "+getSurvey();
    }

}
