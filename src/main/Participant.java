package main;
import java.util.Scanner;

public class Participant extends User{
    private String name;
    public boolean surveyCompleted;
    public Survey survey;
    private String preferredGame;
    private int skillLevel;
    private String preferredRole;
    private int personalityScore;
    public String personalityType;
    TeamFormation teamFormation;

    //Constructors
    public Participant(String id, String name,String email){    //2.1.2 (SD-login)
        super(id,email);
        this.name=name;
        this.surveyCompleted=false;
        this.survey=null;
    }

    public Participant(String id, String name, String email, boolean surveyCompleted, String personalityType) {
        super(id,email);
        this.name=name;
        this.surveyCompleted=surveyCompleted;
        this.personalityType=personalityType;
    }

    //DISPLAYING THE MAIN MENU
    @Override
    public void showMenu(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n===== Participant Menu =====");
            System.out.println("1. View my details");
            System.out.println("2. Complete Survey");
            System.out.println("3. View Assigned Team");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice;

            //validate input
            try{
                choice=Integer.parseInt(scanner.nextLine());
                if(choice<1 || choice>4){
                    System.out.println("Invalid choice. Please enter a number between 1-4.");
                    continue;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid choice. Please enter a number between 1-4.");
                continue;
            }

            switch(choice){
                case 1:         //1.1(SD-view personal details)
                    viewParticipantDetails();       //1.2(SD-view personal details)
                    break;
                case 2:         //1.1(SD-complete survey)
                    if(surveyCompleted){
                        System.out.println("Survey is already completed");  //1.2(SD-complete survey)
                    }else{
                    completeSurvey(); //1.3(SD-complete survey)

                    //concurrency
                    Concurrency concurrency=new Concurrency(TeamFormation.participants);
                    concurrency.processSurveys();
                    concurrency.shutdown();
                    }
                    break;
                case 3:         //1.1(SD-view assigned team)
                    if(teamFormation==null){
                        System.out.println("Teams are not generated yet.");     //1.2(SD-view assigned team)
                    }else {
                        viewTeam();
                    }
                    break;
                case 4:
                    System.out.println("Logging out....");
                    return;

            }
        }
    }


    //VIEW PARTICIPANTS PERSONAL DETAILS
    public void viewParticipantDetails() {          //1.2(SD-view personal details)
        System.out.println("\n===== Participant Details =====");        //1.3(SD-view personal details)
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Survey Completed: " + surveyCompleted);
        if(surveyCompleted){
            System.out.println("Personality Type: " + personalityType);     //1.4(SD-view personal details)
        }else{
            System.out.println("Please complete the survey.");      //1.5(SD-view personal details)
        }
    }

    //SURVEY FOR THE NEWLY REGISTERED PARTICIPANTS
    public void completeSurvey(){   //1.3(SD-complete survey)
        this.survey=Survey.completeSurvey();
        this.surveyCompleted=true;
        this.personalityType=this.survey.personalityType;
        this.preferredGame=this.survey.getInterests();
        this.personalityScore=this.survey.personalityScore;
        this.preferredRole=this.survey.getPreferredRole();
        this.skillLevel=this.survey.skillLevel;

        System.out.println("\n Survey completed!!");    //1.7(SD-complete survey)
    }


    //VIEW THE ASSIGNED TEAM FOR A PARTICIPANT
    public void viewTeam() {
        Team myTeam = TeamFormation.TeamOfParticipant(this.id);     //1.3(SD-view assigned team)

        if(myTeam==null){
            System.out.println("You are not assigned to any team yet.");
        }else{
            System.out.println("\n======== Your Team =========");
            System.out.println(myTeam);     //1.8(SD-view assigned team)
        }
    }

    public String getTeamDisplayInfo(){
        return "ID: "+id+", Name: "+name+", Email: "+email+", Personality: "+personalityType;
    }

    //getters

    public String getName(){
        return name;
    }


    public Survey getSurvey(){
        return survey;
    }

    public int getSkillLevel(){
        return skillLevel;
    }

    public String getPreferredRole(){
        return preferredRole;
    }

    public String getPersonalityType(){
        return personalityType;
    }

    public int getPersonalityScore(){
        return personalityScore;
    }

    public String getPreferredGame() {
        return preferredGame;
    }

    //setters
    public void setPreferredGame(String preferredGame){
        this.preferredGame=preferredGame;
    }

    public void setSkillLevel(int skillLevel){
        this.skillLevel=skillLevel;
    }

    public void setPreferredRole(String preferredRole){
        this.preferredRole=preferredRole;
    }

    public void setPersonalityScore(int personalityScore){
        this.personalityScore=personalityScore;
    }

    public void setPersonalityType(String personalityType){
        this.personalityType=personalityType;
    }

    public void setSurveyCompleted(boolean surveyCompleted){
        this.surveyCompleted=true;
    }

    public void setTeamFormation(TeamFormation teamFormation){
        this.teamFormation=teamFormation;
    }

    //Participant Object
    @Override
    public String toString(){
        String result= "ID: "+id+"\nName: "+name+"\nEmail: "+
                email+"\nSurvey Completed: "+surveyCompleted;

        if(surveyCompleted && survey!=null){
            result= result="\n Survey: \n"+survey.toString();
        }
        return result;
    }

}
