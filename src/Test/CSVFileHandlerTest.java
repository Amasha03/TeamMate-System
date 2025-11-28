package Test;
import main.CSVFileHandler;
import main.Participant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CSVFileHandlerTest {

    @TempDir
    Path tempDir;

    @Test
    void testLoadParticipantCSV() throws IOException{
        //create temporary CSV file
        File csvFile=new File(tempDir.toFile(),"participant_sample.csv");
        FileWriter writer = new FileWriter(csvFile);
        writer.write("ID,Name,Email,PreferredGame,SkillLevel,PreferredRole,PersonalityScore,PersonalityType\n");
        writer.write("P201,Participant_201,user201@university.edu,Valorant,8,Attacker,85,Leader\n");
        writer.close();

        ArrayList<Participant> participants= CSVFileHandler.loadParticipantCSV(csvFile.getAbsolutePath());

        assertNotNull(participants);
        assertEquals(1, participants.size());
        assertEquals("P201",participants.get(0).getId());

    }

    @Test
    void testLoadNonExistentCSV(){
        ArrayList<Participant> participants= CSVFileHandler.loadParticipantCSV("nonExistent.csv");

        assertNotNull(participants);
        assertTrue(participants.isEmpty());
    }

}
