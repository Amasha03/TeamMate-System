package main;
import java.util.*;

public class Login {
    public List<Organizer> organizers;
    public List<Participant> participants;

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
          //  System.out.println("2. Register");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice;

            try{
                choice = Integer.parseInt(input);
                if(choice<1 || choice>2){
                    System.out.println("Invalid choice. Please choose 1 or 2");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
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

        System.out.println("\n======Welcome to TeamMate System======");
        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();
        if (email.isEmpty() || id.isEmpty()) {
            System.out.println("Id and Email cannot be empty! Please enter a valid Id and Email.");
        }

        // Check in Participants
        for (Participant p : participants) {
            if (p.getId().equalsIgnoreCase(id) && p.getEmail().equalsIgnoreCase(email)) {
                System.out.println("\nParticipant Login Successful!");
                return p;
            }
        }

        // Check in Organizers
        for (Organizer o : organizers) {
            if (o.getId().equalsIgnoreCase(id) && o.getEmail().equalsIgnoreCase(email)) {
                System.out.println("\nOrganizer Login Successful!");
                //o.participants=(ArrayList<Participant>) this.participants;
                return o;
            }
        }

        System.out.println("Invalid login!");

        //register to the system
        System.out.print("\nDo you want to register to the system?(yes/no): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            return Register.register((ArrayList<Participant>) this.participants);
        }

        return null;
    }

}

