package main;
import java.util.ArrayList;
import java.util.Scanner;

public class Organizer extends User{
    private String name;
    private String role;
    public ArrayList<Participant> participants;
    public ArrayList<Participant> allParticipants=new ArrayList<>();
    public TeamFormation teamFormation;
    private int teamSize;

    //constructors
    public Organizer(String id, String email, String name, String role) {
        super(id, email);
        this.name=name;
        this.role=role;
        this.teamFormation=new TeamFormation();
    }
/**
    public Organizer(String id, String email, String name, String role,ArrayList<Participant> participants) {
        super(id, email);
        this.name=name;
        this.role=role;
        this.participants=participants;
    }
**/


    //DISPLAYING THE MAIN MENU
    @Override
    public void showMenu() {
        Scanner scanner=new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== Organizer Menu =====");
            System.out.println("1. View Personal Details");
            System.out.println("2. Save CSV");
            System.out.println("3. Upload Participant CSV");
            System.out.println("4. Set Team Size");
            System.out.println("5. Generate Teams");
            System.out.println("6. View Formed Teams");
            System.out.println("7. Logout");
            System.out.print("\nEnter your choice: ");
            choice=scanner.nextInt();
            scanner.nextLine();

            try{
                if(choice<1 || choice>7){
                    System.out.println("Invalid choice. Please enter a number between 1-7.");
                    continue;
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid choice. Please enter a number between 1-7.");
                continue;
            }

            switch (choice) {
                case 1:     //2.1(SD-view personal details)
                    viewOrganizerDetails();     //2.2(view personal details)
                    break;
                case 2:     //1.1(SD-save csv)
                    Scanner sc=new Scanner(System.in);      //1.2(SD-save csv)
                    System.out.println("=====Save CSV ======");
                    System.out.println("1.Save participant CSV");
                    System.out.println("2.Save Formed Teams CSV");
                    System.out.print("Enter your choice: ");
                    int saveChoice=sc.nextInt();
                    sc.nextLine();
                    switch (saveChoice) {
                        case 1:     //1.3(SD-save csv)
                            System.out.println("Number of participants = " + participants.size());
                            CSVFileHandler.saveParticipantsToCSV(participants, "resources/participants.csv");
                            break;
                        case 2:     //2.1(SD-save csv)
                            String savePath="resources/formed_teams.csv";
                            CSVFileHandler.saveTeamsToCSV(savePath);
                            break;
                    }
                    break;

                case 3:         //1.1(SD-upload csv)
                    System.out.println("[If you don't have the file path, Please go to the option 2 to save the participants and to get the file path.]");
                    System.out.print("Enter CSV file path: ");
                    String path = scanner.nextLine();
                    try {
                        allParticipants = CSVFileHandler.loadParticipantCSV(path);         //1.2(SD-upload csv)
                        System.out.println("Successfully loaded " + allParticipants.size() + " participants.");
                    }catch (Exception e) {
                        System.out.println("Failed to load CSV file: " + e.getMessage());
                    }
                    break;
                case 4:         //1.1(SD-define team size)
                    teamSize=TeamFormation.assignTeamSize(scanner);     //1.2(SD-define team size)
                    break;
                case 5:         //1.1(SD-generate teams)
                    if (allParticipants.isEmpty()){
                        System.out.println("Please upload participants file before generating teams.");
                    } else if (teamSize<=2||teamSize>=13) {
                        System.out.println("Please define the team size first!");
                    } else {
                        try{
                            teamFormation.generateTeams(allParticipants, teamSize);     //1.2(SD-generate teams)
                            for (Participant p : allParticipants) {
                                p.setTeamFormation(teamFormation);
                            }
                            //concurrency
                            Concurrency concurrency = new Concurrency(allParticipants);
                            concurrency.formTeams(teamSize);

                        }catch (Exception e) {
                            System.out.println("Failed to generate teams.");
                        }
                    }
                    break;
                case 6:     //1.1(SD-view teams)
                    teamFormation.displayTeams();   //1.2(SD-view teams)
                    break;
                case 7:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(choice != 7);
    }

    //DISPLAY ORGANIZER PERSONAL DETAILS
    public void viewOrganizerDetails() {        //2.2(SD-view personal details)
        System.out.println("\n===== Organizer Details =====");  //2.3(view personal details)
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }
}
