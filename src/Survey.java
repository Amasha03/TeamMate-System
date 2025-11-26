import java.util.*;
import java.util.concurrent.*;

public class Survey {
    int[] surveyScores=new int[5];
    String interests;
    String preferredRole;
    String personalityType;
    int personalityScore;
    int skillLevel;

    //constructor
    public Survey(String interests,String preferredRole,int personalityScore,String personalityType,int skillLevel){
        this.interests=interests;
        this.preferredRole=preferredRole;
        this.personalityScore=personalityScore;
        this.personalityType=personalityType;
        this.skillLevel=skillLevel;
    }


    //Getters
    public String getInterests(){
        return interests;
    }
    public String getPreferredRole(){
        return preferredRole;
    }
    public int getSkillLevel(){
        return skillLevel;
    }

    //SURVEY QUESTIONS
    public static Survey completeSurvey() {
        Scanner scanner=new Scanner(System.in);
        int[] surveyScores = new int[5];


        System.out.println("==========MEMBER SURVEY==========");
        System.out.println("Personality Questions:");
        System.out.println("Rate each statement from 1 (Strongly Disagree) to 5 (Strongly Agree).");
        String[] questions={
                "Q1: I enjoy taking the lead and guiding others during group activities.",
                "Q2: I prefer analyzing situations and coming up with strategic solutions.",
                "Q3: I work well with others and enjoy collaborative teamwork.",
                "Q4: I am calm under pressure and can help maintain team morale.",
                "Q5: I like making quick decisions and adapting in dynamic situations."
        };

        for(int i=0;i<5;i++){
            while(true){
                System.out.println(questions[i]);
                String input=scanner.nextLine();
            try{
                int score=Integer.parseInt(input);
                if(score>=0 && score<=5){
                    surveyScores[i]=score;
                    break;
                }else{
                    System.out.println("Invalid answer. Please enter a number between 1 and 5.");
            }
            }catch(NumberFormatException e){
                System.out.println("Invalid answer. Please enter a number between 1 and 5.");}
            }
        }

        //Interests
        String interests;
        while(true){
            System.out.println("Enter your game interest:[Valorant,Dota,FIFA,Basketball,Badminton]");
            interests = scanner.nextLine();
            if(interests.isEmpty()){
                System.out.println("Interests cannot be empty");
            }else{
                break;
            }
        }


        //Preferred role
        System.out.println("\n[Strategist: Focuses on tactics and planning. Keeps the bigger picture in mind during gameplay]");
        System.out.println("[Attacker: Frontline player. Good reflexes, offensive tactics, quick execution]");
        System.out.println("[Defender: Protects and supports team stability. Good under pressure and team-focused]");
        System.out.println("[Supporter: Jack-of-all-trades. Adapts roles, ensures smooth coordination]");
        System.out.println("[Coordinator: Communication lead. Keeps the team informed and organized in real time]");
        String role;
        while(true){
            System.out.println("\nEnter your preferred role: (eg:Strategist,Attacker,Defender,Supporter,Coordinator)");
            role=scanner.nextLine();
            if(role.isEmpty()){
                System.out.println("Roles cannot be empty");
            }else {
                break;
            }
        }

        //Skill level
        int skillLevel;
        while(true){
            System.out.println("Enter your skill level (1-10): ");
            String input=scanner.nextLine();
            try{
                skillLevel=Integer.parseInt(input);
                if(skillLevel<1||skillLevel>10){
                    System.out.println("Invalid skill level. Please enter a number between 1-10.");
                    continue;
                }
                break;
        }catch (NumberFormatException e){
            System.out.println("Invalid skill level. Please enter a number between 1 and 10.");}
        }

        //calculate personality score
            int personalityScore=0;
            for(int score : surveyScores)
                personalityScore +=score;
            personalityScore *=4;

        String personalityType;
        if(personalityScore >90)
            personalityType="Leader";
        else if (personalityScore>70)
            personalityType="Balanced";
        else if (personalityScore>50)
            personalityType="Thinker";
        else
            personalityType="Unknown";



        //Display full info
        System.out.println("\n------member survey result------");
        System.out.println("\nInterests: "+interests+"\nPreferred Role: "+role+"\nPersonality Score: "+personalityScore+"\nPersonality Type: "+ personalityType+"\nSkill Level: "+skillLevel);


        Survey survey = new Survey(interests,role,personalityScore, personalityType, skillLevel);
        survey.surveyScores=surveyScores;
        return survey;


    }


    //Survey Object
    @Override
    public String toString(){
        return "\n Preferred Role: "+getPreferredRole()+"\n Interests: "+getInterests()+"\n Skill Level: "+getSkillLevel();
    }
    
}
