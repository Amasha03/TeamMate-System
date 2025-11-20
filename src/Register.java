import java.util.*;

public class Register {

    //REGISTER A NEW PARTICIPANT
    public static Participant register(ArrayList<Participant> participants) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Register New Account =====");

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        // Check if ID already exists
        for (Participant p : participants) {
            if (p.getId().equals(id)) {
                System.out.println("This ID is already taken!");
                return null;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();


        // Create new participant
        Participant p = new Participant(id, name, email);
        participants.add(p);

        System.out.println("Registration Successful!");
        return p;
    }
}
