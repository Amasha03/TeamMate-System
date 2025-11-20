import java.util.*;

public class Login {
    private List<Organizer> organizers;
    private List<Participant> participants;

    //constructor
    public Login(List<Organizer> organizers, List<Participant> participants) {
        this.organizers = organizers;
        this.participants = participants;
    }

    //MAIN HOME SCREEN
    public void showHomeScreen() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("====== TeamMate System ======");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice;

            try{
                choice = Integer.parseInt(input);
                if(choice<1 || choice>3){
                    System.out.println("Invalid choice. Please enter a number between 1-4.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number between 1-3.");
                continue;
            }

            switch (choice) {
                case 1:
                    User user = login();   // <-- returns Participant OR Organizer
                    if (user != null) {
                        user.showMenu();  // polymorphic menu
                    }
                    break;

                case 2:
                    Register.register((ArrayList<Participant>) participants);

                    break;

                case 3:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    //LOGIN TO SYSTEM USING ID  AND EMAIL
    public User login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("======Welcome to TeamMate System======");
        System.out.println("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter your Email: ");
        String email = scanner.nextLine();

        // Check in Participants
        for (Participant p : participants) {
            if (p.getId().equalsIgnoreCase(id) && p.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Participant Login Successful!");
                return p;
            }
        }

        // Check in Organizers
        for (Organizer o : organizers) {
            if (o.getId().equalsIgnoreCase(id) && o.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Organizer Login Successful!");
                o.participants=(ArrayList<Participant>) this.participants;
                return o;
            }
        }

        System.out.println("Invalid login!");
        return null;
    }

}

