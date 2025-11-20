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
            System.out.println("2. Save Participants to CSV");
            System.out.println("3. Upload Participant CSV");
            System.out.println("4. Set Team Size");
            System.out.println("5. Generate Teams");
            System.out.println("6. Save Teams to CSV");
            System.out.println("7. View Formed Teams");
            System.out.println("8. Logout");
            System.out.println("Enter your choice");
            choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewOrganizerDetails();
                    break;
                case 2:
                    System.out.println("Number of participants = " + participants.size());
                    CSVFileHandler.saveParticipantsToCSV(participants, "resources/participants.csv");
                    break;
                case 3:
                    System.out.println("Enter CSV file path: ");
                    String path = scanner.nextLine();
                    participants = CSVFileHandler.loadParticipantCSV(path);
                    System.out.println("Successfully loaded " + participants.size() + " participants.");
                    break;
                case 4:
                    System.out.println("Enter team size: ");
                    teamSize = Integer.parseInt(scanner.nextLine());
                    if (teamSize <= 0) {
                        System.out.println("Invalid team size.");
                    } else {
                        System.out.println("Successfully set team size to " + teamSize);
                    }

                    break;
                case 5:
                    if (participants.isEmpty()) {
                        System.out.println("Please upload participants file.");
                    } else {
                        teamFormation.generateTeams(participants, teamSize);

                        for (Participant p : participants) {
                            p.setTeamFormation(teamFormation);
                        }
                    }

                    break;
                case 6:
                    String savePath="resources/formed_teams.csv";
                    teamFormation.saveTeamsToCSV(savePath);
                    break;
                case 7:
                    teamFormation.displayTeams();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(choice != 8);
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
