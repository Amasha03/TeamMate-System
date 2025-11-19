import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Organizer extends User{
    private String name;
    private String role;
    public ArrayList<Participant> participants=new ArrayList<>();
    private TeamFormation teamFormation;
    private int teamSize;

    public Organizer(String id, String email, String name, String role) {
        super(id, email);
        this.name=name;
        this.role=role;
        this.teamFormation=new TeamFormation();
    }

    public Organizer(String id, String email, String name, String role,ArrayList<Participant> participants) {
        super(id, email);
        this.name=name;
        this.role=role;
        this.participants=participants;
    }

    public static ArrayList<Organizer> getOrganizers(String filePath){
        ArrayList<Organizer> organizers=new ArrayList<Organizer>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            br.readLine();

            while((line=br.readLine()) != null){
                String[] data=line.split(",");

                String id=data[0].trim();
                String name=data[1].trim();
                String email=data[2].trim();
                String role=data[3].trim();

                Organizer organizer=new Organizer(id,email,name,role);
                organizers.add(organizer);
            }
        }catch (Exception e){
            System.out.println("Error reading file");
        }
        return organizers;
    }

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
                    System.out.println("DEBUG -> participant size = "+participants.size());
                    ParticipantCSV.saveParticipantsToCSV(participants,"resources/participants.csv");
                    break;
                case 3:
                    System.out.println("Enter CSV file path: ");
                    String path=scanner.nextLine();
                    participants=ParticipantCSV.loadParticipantCSV(path);
                    System.out.println("Successfully loaded "+participants.size()+" participants.");
                    break;
                case 4:
                    System.out.println("Enter team size: ");
                    teamSize=Integer.parseInt(scanner.nextLine());
                    if(teamSize<=0){
                        System.out.println("Invalid team size.");
                    }else {
                        System.out.println("Successfully set team size to "+teamSize);
                    }

                    break;
                case 5:
                    if (participants.isEmpty()){
                        System.out.println("Please upload participants file.");
                    }else{
                        teamFormation.generateTeams(participants,teamSize);
                    }
                    break;
                case 6:
                    System.out.println("Enter CSV file path to save teams: ");
                    String savePath=scanner.nextLine();
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
    public void viewOrganizerDetails() {
        System.out.println("\n===== Organizer Details =====");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }
}
