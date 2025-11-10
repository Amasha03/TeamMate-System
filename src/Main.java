import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your ID:");
        String participantID = scanner.nextLine();

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        //personality questions
        int[] personalityScores = new int[5];
        System.out.println("**********MEMBER SURVEY**********");
        System.out.println("Personality Questions:");
        System.out.println("Rate each statement from 1 (Strongly Disagree) to 5 (Strongly Agree).");
        int personalityScore;

        do {
            System.out.println("Q1: I enjoy taking the lead and guiding others during group activities.");
            personalityScore = Integer.parseInt(scanner.nextLine());
        } while (personalityScore < 1 || personalityScore > 5);
        personalityScores[0] = personalityScore;

        do {
            System.out.println("Q2: I prefer analyzing situations and coming up with strategic solutions.");
            personalityScore = Integer.parseInt(scanner.nextLine());
        } while (personalityScore < 1 || personalityScore > 5);
        personalityScores[1] = personalityScore;

        do {
            System.out.println("Q3: I work well with others and enjoy collaborative teamwork.");
            personalityScore = Integer.parseInt(scanner.nextLine());
        } while (personalityScore < 1 || personalityScore > 5);
        personalityScores[2] = personalityScore;

        do {
            System.out.println("Q4: I am calm under pressure and can help maintain team morale.");
            personalityScore = Integer.parseInt(scanner.nextLine());
        } while (personalityScore < 1 || personalityScore > 5);
        personalityScores[3] = personalityScore;

        do {
            System.out.println("Q5: I like making quick decisions and adapting in dynamic situations.");
            personalityScore = Integer.parseInt(scanner.nextLine());
        } while (personalityScore < 1 || personalityScore > 5);
        personalityScores[4] = personalityScore;


        //Interests
        System.out.println("Enter your game interests:(eg:Valorant,Dota,FIFA,Basketball,Badminton)");
        String interests = scanner.nextLine();

        //Preferred role
        System.out.println("Enter your preferred role: (eg:Strategist,Attacker,Defender,Supporter,Coordinator)");
        String role = scanner.nextLine();

        //create objects
        Survey survey = null;
        survey = new Survey(personalityScores, interests, role);
        Participant member = new Participant(participantID, name, email, true, survey);


        //Display full info
        System.out.println("\n------member survey result------");
        System.out.println(member);


        // **** HAVE TO HANDLE EXCEPTIONS ****



    }
}
