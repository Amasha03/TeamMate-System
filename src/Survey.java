import java.util.*;

public class Survey {
    int[] surveyScores=new int[5];
    static int personalityScore;
    String interests;
    String preferredRole;

    //constructor
    public Survey(int personalityScore,String interests,String preferredRole){
        this.personalityScore=personalityScore;
        this.interests=interests;
        this.preferredRole=preferredRole;
    }

    //calculate personality score
    public int calSurveyScore(){
        int total=0;
        int totalScore=0;
        for(int i=0;i<surveyScores.length;i++){
            total+=surveyScores[i];
            totalScore=total*4;
        }
        return totalScore;
    }

    //classify personality
    public String classifyPersonality(){
        int score=calSurveyScore();
        if(score>=90)
            return "Leader";
        else if(score>=70)
            return "Balanced";
        else if (score>=50)
            return "Thinker";
        else
            return "unknown";
    }

    //Getters
    public String getInterests(){
        return interests;
    }
    public String getPreferredRole(){
        return preferredRole;
    }

    //personality questions
    public static Survey completeSurvey() {
        Scanner scanner=new Scanner(System.in);
        int[] surveyScores = new int[5];


        System.out.println("**********MEMBER SURVEY**********");
        System.out.println("Personality Questions:");
        System.out.println("Rate each statement from 1 (Strongly Disagree) to 5 (Strongly Agree).");
        int surveyScore;

        do {
            System.out.println("Q1: I enjoy taking the lead and guiding others during group activities.");
            surveyScore = Integer.parseInt(scanner.nextLine());
        } while (surveyScore < 1 || surveyScore > 5);
        surveyScores[0] = surveyScore;

        do {
            System.out.println("Q2: I prefer analyzing situations and coming up with strategic solutions.");
            surveyScore = Integer.parseInt(scanner.nextLine());
        } while (surveyScore < 1 || surveyScore > 5);
        surveyScores[1] = surveyScore;

        do {
            System.out.println("Q3: I work well with others and enjoy collaborative teamwork.");
            surveyScore = Integer.parseInt(scanner.nextLine());
        } while (surveyScore < 1 || surveyScore > 5);
        surveyScores[2] = surveyScore;

        do {
            System.out.println("Q4: I am calm under pressure and can help maintain team morale.");
            surveyScore = Integer.parseInt(scanner.nextLine());
        } while (surveyScore < 1 || surveyScore > 5);
        surveyScores[3] = surveyScore;

        do {
            System.out.println("Q5: I like making quick decisions and adapting in dynamic situations.");
            surveyScore = Integer.parseInt(scanner.nextLine());
        } while (surveyScore < 1 || surveyScore > 5);
        surveyScores[4] = surveyScore;


        //Interests
        System.out.println("Enter your game interests:(eg:Valorant,Dota,FIFA,Basketball,Badminton)");
        String interests = scanner.nextLine();

        //Preferred role
        System.out.println("Enter your preferred role: (eg:Strategist,Attacker,Defender,Supporter,Coordinator)");
        String role = scanner.nextLine();


        //create objects
        //this.survey = new Survey(personalityScores, interests, role);
        //Participant member = new Participant(id, name, email);

        //Display full info
        System.out.println("\n------member survey result------");
        System.out.println("\nInterests: "+interests+"\nPreferred Role: "+role);

        return new Survey(personalityScore,interests, role);
    }
    @Override
    public String toString(){
        return "\n Preferred Role: "+getPreferredRole()+"\n Interests: "+getInterests();
    }
    
}
