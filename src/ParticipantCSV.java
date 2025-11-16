import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ParticipantCSV {
    public static ArrayList<Participant> loadParticipantCSV(String filePath)
    {
        ArrayList<Participant> participants=new ArrayList<>();

        try(BufferedReader br=new BufferedReader(new FileReader(filePath))){
        String line;
        br.readLine();

            while((line=br.readLine()) !=null){
                String[] data = line.split(",");

                if(data.length>=3) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    String email = data[2].trim();


                    boolean surveyCompleted = false;
                    String personalityType = null;
                    if (data[7] != null) {
                        surveyCompleted = true;
                        personalityType = data[7].trim();
                    }
                    Participant p=new Participant(id, name, email, surveyCompleted, personalityType);
                   participants.add(p);
                    /**
 if(data.length>=10){
 int[] scores = new int[5];
 for(int i=0;i<5;i++){
 scores[i]=Integer.parseInt(data[3+i].trim());
 }

 String preferredGame=data[3].trim();
 String preferredRole = data[5].trim();
 // int personalityScore=Integer.parseInt(data[7].trim());
 //String personalityType=data[8].trim();

 Survey survey=new Survey(Survey.personalityScore,preferredGame,preferredRole);
 p.survey=survey;
 p.surveyCompleted=true;
 p.setPersonalityType(survey.classifyPersonality());
 }

 participants.add(p);
 **/
                }
            }
        } catch (Exception e) {
            System.out.println("Error in reading file");
        }
        return participants;
    }
    //validate login based on csv data
    public static Participant login(String id, String email,ArrayList<Participant> participants){
        for (Participant p : participants) {
            if(p.getId().equalsIgnoreCase(id)&&p.getEmail().equalsIgnoreCase(email)){
                return p;
            }
        }
        return null;
    }

/**
    public static ArrayList<Participant> loadFullData(String fileName){
        ArrayList<Participant> participants=new ArrayList<>();

        try(BufferedReader br=new BufferedReader(new FileReader(fileName))){
            br.readLine();
            String line;
            while ((line=br.readLine()) != null){
                String[] data=line.split(",");

                if (data[0].trim().equalsIgnoreCase(id)) {

                    String name = data[1].trim();
                    String email = data[2].trim();

                    Participant p = new Participant(id, name, email);

                    if (data.length > 3) {
                        p.setSurveyCompleted(true);

                        if (data.length > 3)
                            p.setPreferredGame(data[3].trim());
                        if (data.length > 4)
                            p.setSkillLevel(Integer.parseInt(data[4].trim()));
                        if (data.length > 5)
                            p.setPreferredGame(data[5].trim());
                        if (data.length > 6)
                            p.setPersonalityScore(Integer.parseInt(data[6].trim()));
                        if (data.length > 7)
                            p.setPersonalityType(data[7].trim());


                    }
                }
            }
        }catch(Exception e){
            System.out.println("Error occurred in loading data.");
        }
        return null;
    }**/
    }
