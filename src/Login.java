import java.util.*;
public class Login {
    private List<Organizer> organizer;
    private List<Participant>  participant;

    public Login(List<Organizer> organizer, List<Participant> participant) {
        this.organizer = organizer;
        this.participant = participant;
    }

    public static Participant login(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("======Welcome to TeamMate System======");
        System.out.println("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter your Email: ");
        String email = scanner.nextLine();

        if(id.toLowerCase().startsWith("o") && email.toLowerCase().endsWith("@university.org.edu")){
            System.out.println("Successfully logged in as an organizer!");
        }
        else if(id.toLowerCase().startsWith("p") && email.toLowerCase().endsWith("@university.edu")){
            System.out.println("Successfully logged in as a participant!");
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            return new Participant(id, name, email);
        }
        else{
            System.out.println("Invalid ID or email.");
        }
        return null;
    }
}
