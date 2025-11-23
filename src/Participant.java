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
    public Participant(String id, String name,String email){
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
            System.out.println("3. View Formed Teams");
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
                case 1:
                    viewParticipantDetails();
                    break;
                case 2:
                    if(surveyCompleted){
                        System.out.println("Survey is already completed");
                    }else{
                    completeSurvey();}
                    break;
                case 3:
                    viewTeam();
                    break;
                case 4:
                    System.out.println("Logging out....");
                    return;

            }
        }
    }


    //VIEW PARTICIPANTS PERSONAL DETAILS
    public void viewParticipantDetails() {
        System.out.println("\n===== Participant Details =====");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Survey Completed: " + surveyCompleted);
        if(surveyCompleted){
            System.out.println("Personality Type: " + personalityType);
        }else{
            System.out.println("Please complete the survey.");
        }
    }

    //SURVEY FOR THE NEWLY REGISTERED PARTICIPANTS
    public void completeSurvey(){
        this.survey=Survey.completeSurvey();
        this.surveyCompleted=true;
        this.personalityType=this.survey.personalityType;
        this.preferredGame=this.survey.getInterests();
        this.personalityScore=this.survey.personalityScore;
        this.preferredRole=this.survey.getPreferredRole();
        this.skillLevel=this.survey.skillLevel;

        System.out.println("\n Survey completed!!");
    }


    //VIEW THE ASSIGNED TEAM FOR A PARTICIPANT
    public void viewTeam() {
        Team myTeam = TeamFormation.TeamOfParticipant(this.id);

        if(myTeam==null){
            System.out.println("You are not assigned to any team yet.");
        }else{
            System.out.println("\n======== Your Team =========");
            System.out.println(myTeam);
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
