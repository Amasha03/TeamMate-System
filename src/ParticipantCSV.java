import java.io.*;
import java.util.*;

//---------LOAD PARTICIPANT CSV TO GET PARTICIPANT DATA------------------
public class ParticipantCSV {
    public ParticipantCSV() throws IOException {
    }

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
                    String preferredName = data[3].trim();
                    int skillLevel = Integer.parseInt(data[4].trim());
                    String preferredRole = data[5].trim();
                    int personalityScore = Integer.parseInt(data[6].trim());
                    String personalityType = data[7].trim();

                    boolean surveyCompleted = true;

                    Participant p=new Participant(id, name, email, surveyCompleted, personalityType);
                    p.setPreferredGame(preferredName);
                    p.setSkillLevel(skillLevel);
                    p.setPreferredRole(preferredRole);
                    p.setPersonalityScore(personalityScore);

                    participants.add(p);

                }
            }
        } catch (Exception e) {
            System.out.println("Error in reading file");
        }
        return participants;
    }
    //-------------VALIDATE THE PARTICIPANT BASED ON CSV DATA--------------
    public static Participant login(String id, String email,ArrayList<Participant> participants){
        for (Participant p : participants) {
            if(p.getId().equalsIgnoreCase(id)&&p.getEmail().equalsIgnoreCase(email)){
                return p;
            }
        }
        return null;
    }




    //---------------SAVE REGISTERED PARTICIPANTS TO CSV---------------------
    public static void saveParticipantsToCSV(ArrayList<Participant> participants,String pFilePath){
        if(participants==null ||participants.isEmpty()){
            System.out.println("No participants to save!");
            System.out.println("File Path: "+pFilePath);
            return;
        }
        try{
            FileWriter fileWriter = getFileWriter(participants, pFilePath);
            fileWriter.close();
            System.out.println("Participants successfully saved to : "+ pFilePath);
        }catch (IOException e){
            System.out.println("Error in writing to file"+e.getMessage());
        }
    }

    private static FileWriter getFileWriter(ArrayList<Participant> participants, String pFilePath) throws IOException {
        FileWriter fileWriter = new FileWriter(pFilePath);

        fileWriter.write("ID,Name,Email,PreferredGame,SkillLevel,PreferredRole,PersonalityScore,PersonalityType\n");
        for(Participant p : participants){
            fileWriter.write(p.getId()+","+
                    p.getName()+","+ p.getEmail()+","+ p.getPreferredGame() +","+ p.getSkillLevel()+","+ p.getPreferredRole()+","+p.getPersonalityScore()+"," +p.getPersonalityType()+"\n");

        }
        return fileWriter;
    }


}
