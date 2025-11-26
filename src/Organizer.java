import java.util.ArrayList;
import java.util.Scanner;

public class Organizer extends User{
    private String name;
    private String role;
    public ArrayList<Participant> participants=new ArrayList<>();
    private TeamFormation teamFormation;
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
            System.out.println("===== Organizer Menu =====");
            System.out.println("1. View Personal Details");
            System.out.println("2. Save CSV");
            System.out.println("3. Upload Participant CSV");
            System.out.println("4. Set Team Size");
            System.out.println("5. Generate Teams");
            System.out.println("6. View Formed Teams");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewOrganizerDetails();
                    break;
                case 2:
                    Scanner sc=new Scanner(System.in);
                    System.out.println("=====Save CSV ======");
                    System.out.println("1.Save participant CSV");
                    System.out.println("2.Save Formed Teams CSV");
                    System.out.print("Enter your choice: ");
                    int saveChoice=sc.nextInt();
                    sc.nextLine();
                    switch (saveChoice) {
                        case 1:
                            System.out.println("Number of participants = " + participants.size());
                            CSVFileHandler.saveParticipantsToCSV(participants, "resources/participants.csv");
                            break;
                        case 2:
                            String savePath="resources/formed_teams.csv";
                            CSVFileHandler.saveTeamsToCSV(savePath);
                            break;
                    }
                    break;

                case 3:
                    System.out.print("Enter CSV file path: ");
                    String path = scanner.nextLine();
                    try {
                        participants = CSVFileHandler.loadParticipantCSV(path);
                        System.out.println("Successfully loaded " + participants.size() + " participants.");
                    }catch (Exception e) {
                        System.out.println("Failed to load CSV file: " + e.getMessage());
                    }
                case 4:
                    System.out.print("Enter team size: ");
                    try{
                        teamSize = Integer.parseInt(scanner.nextLine());
                            if (teamSize <= 2) {
                            System.out.println("Invalid team size. [Minimum team size should be 3]");
                        }

                    }catch(NumberFormatException e){
                        System.out.println("Invalid input! Enter a numeric team size.");
                    }
                    System.out.println("\nSuccessfully set team size to " + teamSize);


                    break;
                case 5:
                    if (participants.isEmpty()) {
                        System.out.println("Please upload participants file before generating teams.");
                    } else {
                        try{
                            teamFormation.generateTeams(participants, teamSize);

                            for (Participant p : participants) {
                                p.setTeamFormation(teamFormation);
                            }
                        }catch (Exception e) {
                            System.out.println("Failed to generate teams.");
                        }
                    }
                    break;
                case 6:
                    teamFormation.displayTeams();
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
    public void viewOrganizerDetails() {
        System.out.println("\n===== Organizer Details =====");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }
}
