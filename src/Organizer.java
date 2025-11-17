import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Organizer extends User{
    private String name;
    private String role;
    public Organizer(String id, String email, String name, String role) {
        super(id, email);
        this.name=name;
        this.role=role;
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
            System.out.println("2. Upload Participant CSV");
            System.out.println("3. View Participants");
            System.out.println("4. Set Team Size");
            System.out.println("5. Generate Teams");
            System.out.println("6. Save Teams to CSV");
            System.out.println("7. View Formed Teams");
            System.out.println("8. Logout");
            System.out.println("Enter your choice");
            choice=scanner.nextInt();
            switch (choice) {
                case 1:
                    viewOrganizerDetails();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
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
