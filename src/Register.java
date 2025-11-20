import java.util.*;

public class Register {

    //REGISTER A NEW PARTICIPANT
    public static Participant register(ArrayList<Participant> participants) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Register New Account =====");

        String id;
        while (true) {
            System.out.print("Enter ID: ");
            id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("ID cannot be empty");
                break;
            } else if (!id.startsWith("P") && !id.startsWith("p")) {
                System.out.println("Invalid ID");
                break;
            }

            // Check if ID already exists
            for (Participant p : participants) {
                if (p.getId().equalsIgnoreCase(id)) {
                    System.out.println("This ID is already taken!");
                    return null;
                }
            }

            String name;
            while (true) {
                System.out.print("Enter Name: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty");
                } else {
                    break;
                }
            }

            //Check if name is already exists
            for (Participant p : participants) {
                if (p.getId().equalsIgnoreCase(name)) {
                    System.out.println("This ID is already taken!");
                    return null;
                }
            }

            String email;
            while (true) {
                System.out.print("Enter Email: ");
                email = scanner.nextLine().trim().toLowerCase();

                String[] parts = email.split("@");
                if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty() || !parts[1].contains("university.edu")) {
                    System.out.println("Invalid email format.");
                } else {
                    break;
                }

            }
            // Create new participant
            Participant p = new Participant(id, name, email);
            participants.add(p);

            System.out.println("Registration Successful!");
            return p;
        }
        return null;
    }
}
