import java.util.*;

public class Login {
    private List<Organizer> organizers;
    private List<Participant>  participants;

    public Login(List<Organizer> organizers, List<Participant> participants) {
        this.organizers = organizers;
        this.participants = participants;
    }

 // ------- MAIN HOME SCREEN ----------
    public void showHomeScreen() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("====== TeamMate System ======");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    User user = login();   // <-- returns Participant OR Organizer
                    if (user != null) {
                        user.showMenu();  // polymorphic menu
                    }
                break;

                case "2":
                    Participant p=Register.register((ArrayList<Participant>) participants);
                        break;

                case "3":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public User login(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("======Welcome to TeamMate System======");
        System.out.println("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter your Email: ");
        String email = scanner.nextLine();

        // Check in Participants
        for (Participant p : participants) {
            if (p.getId().equals(id) && p.getEmail().equals(email)) {
                System.out.println("Participant Login Successful!");
                return p;
            }
        }

        // Check in Organizers
        for (Organizer o : organizers) {
            if (o.getId().equals(id) && o.getEmail().equals(email)) {
                System.out.println("Participant Login Successful!");
                return o;
            }
        }

        // Check in Organizers
        //for (Organizer o : organizers) {
        //  if (o.getId().equals(id) && o.getEmail().equals(email)) {
        //    System.out.println("Organizer Login Successful!");
        //  return o;
        //  }
        //   }

        System.out.println("Invalid login!");
        return null;
    }

}
/**
import java.util.*;

public class Login {
    private List<Organizer> organizers;
    private List<Participant> participants;

    public Login(List<Organizer> organizers, List<Participant> participants) {
        this.organizers = organizers;
        this.participants = participants;
    }

    // ------- MAIN HOME SCREEN ----------
    public void showHomeScreen() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("====== TeamMate System ======");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    User user = login();   // <-- returns Participant OR Organizer
                    if (user != null) {
                        user.showMenu();  // polymorphic menu
                    }
                    break;

                case "2":
                    register(scanner);
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // --------- LOGIN HANDLER FOR BOTH USERS ----------
    public User login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== LOGIN ======");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        // Check in Participants
        for (Participant p : participants) {
            if (p.getId().equals(id) && p.getEmail().equals(email)) {
                System.out.println("Participant Login Successful!");
                return p;
            }
        }

        // Check in Organizers
        //for (Organizer o : organizers) {
          //  if (o.getId().equals(id) && o.getEmail().equals(email)) {
            //    System.out.println("Organizer Login Successful!");
              //  return o;
          //  }
     //   }

        System.out.println("Invalid login!");
        return null;
    }

    // ----------- REGISTER -----------
    public void register(Scanner scanner) {
        System.out.println("Register Feature Coming Soon...");
    }
}
 **/

