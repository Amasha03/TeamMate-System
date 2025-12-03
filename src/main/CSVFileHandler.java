package main;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVFileHandler {
    public CSVFileHandler() throws IOException {
    }

    private static final Logger logger = Logger.getLogger(CSVFileHandler.class.getName());

    //LOAD PARTICIPANT CSV TO GET PARTICIPANT DATA
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
            logger.info("Loaded "+participants.size()+" participants from "+filePath);
        }
        catch(FileNotFoundException nf){
            logger.severe("File not found: "+filePath);
        }catch(IOException ioe){
            logger.severe("Error reading file: "+filePath+"-"+ioe.getMessage());
        }
        catch (Exception e) {
            logger.log(Level.SEVERE,"Unexpected error occurred.",e);
        }
        return participants;
    }

    //LOAD ORGANIZER CSV TO GET ORGANIZER DATA
    public static ArrayList<Organizer> getOrganizers(String filePath){
        ArrayList<Organizer> organizers=new ArrayList<Organizer>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            br.readLine();

            while((line=br.readLine()) != null){
                String[] data=line.split(",");

                String id=data[0].trim();
                String name=data[1].trim();
                String email=data[2].trim();
                String role=data[3].trim();

                Organizer organizer=new Organizer(id,email,name,role);
                organizers.add(organizer);
            }
            logger.info("Loaded "+organizers.size()+" organizers from "+filePath);
        }
        catch(FileNotFoundException nf){
            logger.severe("File not found : "+filePath);
        }catch(IOException ioe){
            logger.severe("Error reading file: "+filePath+"-"+ioe.getMessage());
        }catch (Exception e){
            logger.log(Level.SEVERE,"Unexpected error occurred.",e);
        }
        return organizers;
    }


    //SAVE REGISTERED PARTICIPANTS TO CSV
    public static void saveParticipantsToCSV(ArrayList<Participant> participants,String pFilePath){     //1.3(SD-save csv)
        if(participants==null ||participants.isEmpty()){
            System.out.println("No participants to save!");     //1.4(SD-save csv)
            return;
        }
        try{
            FileWriter fileWriter = getFileWriter(participants, pFilePath);     //1.5(SD-save csv)
            fileWriter.close();
            System.out.println("Participants successfully saved to : "+ pFilePath);     //1.8(SD-save csv)
        }catch (IOException e){
            System.out.println("Error in writing to file"+e.getMessage());
        }catch (Exception e){
            System.out.println("Unexpected error occurred.");
        }
    }

    //CREATE PARTICIPANT CSV IN STRUCTURED FORMAT
    private static FileWriter getFileWriter(ArrayList<Participant> participants, String pFilePath) throws IOException {     //1.5(SD-save csv)
        FileWriter fileWriter = new FileWriter(pFilePath);

        fileWriter.write("ID,Name,Email,PreferredGame,SkillLevel,PreferredRole,PersonalityScore,PersonalityType\n");
        for(Participant p : participants){      //1.6(SD-save csv)
            fileWriter.write(p.getId()+","+
                    p.getName()+","+ p.getEmail()+","+ p.getPreferredGame() +","+ p.getSkillLevel()+","+ p.getPreferredRole()+","+p.getPersonalityScore()+"," +p.getPersonalityType()+"\n");

        }
        return fileWriter;  //1.7(SD-save csv)
    }

    //SAVE GENERATED TEAMS TO A CSV FILE
    public static void saveTeamsToCSV(String filePath){     //2.1(SD-save csv)
        String teamsFilePath="resources/formed_teams.csv";

        if (TeamFormation.teams==null || TeamFormation.teams.isEmpty()){
            System.out.println("No teams to save!");    //2.2(SD-save csv)
            return;
        }
        try{
            File directory = new File("resources");
            if(!directory.exists()){
                if(!directory.mkdirs()){
                    logger.severe("Error creating directory");
                    return;
                }
            }
            FileWriter fw = new FileWriter(teamsFilePath); //2.3(SD-save csv)
            fw.write("Team Id,Participant Id,Participant Name,Preferred Game,Preferred Role,Personality Type,Skill Level\n");
            int teamId=1;
            for(Team team: TeamFormation.teams){    //2.4(SD-save csv)
                for(Participant participant: team.members){
                    fw.write(teamId+","+participant.id+","+participant.getName()+","+participant.getPreferredGame()+","+participant.getPreferredRole()+","+participant.getPersonalityType()+","+participant.getSkillLevel()+"\n");

                }
                teamId++;
                fw.write("\n");
            }
            fw.close();

            logger.severe("Teams successfully saved to  "+teamsFilePath);   //2.6(SD-save csv)
        }catch (IOException e){
            logger.severe("Error occurred in saving CSV: "+e.getMessage());
        }catch(Exception e){
            logger.log(Level.SEVERE,"Unexpected error occurred.");
        }
    }


}
