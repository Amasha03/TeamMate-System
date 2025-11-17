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


    }
