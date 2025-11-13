import java.util.Scanner;

public class Participant extends User{
    private String name;
    public boolean surveyCompleted;
    public Survey survey;
    private String personalityType;

    //Constructor
    public Participant(String id, String name,String email){
        super(id,email);
        this.name=name;
        this.surveyCompleted=false;
        this.survey=null;
    }

    public void participantMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("\n===== Participant Menu =====");
            System.out.println("1. View my details");
            System.out.println("2. Complete Survey");
            System.out.println("3. View Formed Teams");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice=Integer.parseInt(scanner.nextLine());

            switch(choice){
                case 1:
                    viewParticipantDetails();
                    break;
                case 2:
                    completeSurvey();
                    break;
                case 3:
                    viewFormedTeams();
                    break;
                case 4:
                    System.out.println("Logging out....");
                    break;
                default:
                    System.out.println("Invalid choice.(Please only choose 1-3)");
            }
        }while(choice != 4);
    }


    private void viewParticipantDetails() {
        System.out.println("\n===== Participant Details =====");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Survey Completed: " + surveyCompleted);
        if(surveyCompleted){
            System.out.println(getSurvey());
            System.out.println("Personality Type: " + personalityType);
        }else{
            System.out.println("Please complete the survey.");
        }
    }

    public void completeSurvey(){
        this.survey=survey.completeSurvey();
        this.surveyCompleted=true;
        this.personalityType=survey.classifyPersonality();
        System.out.println("\n Survey completed!!");
    }


    private void viewFormedTeams() {
    }


    //getters

    public String getName(){
        return name;
    }


    public Survey getSurvey(){
        return survey;
    }

    @Override
    public String toString(){
        return "ID: "+id+"\nName: "+name+"\nEmail: "+
                email+"\nSurvey Completed: "+surveyCompleted+
                "\nSurvey:\n "+getSurvey();
    }

}
