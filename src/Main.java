import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Participant> participants =
                ParticipantCSV.loadParticipantCSV("resources/participants_sample.csv");

        ArrayList<Organizer> organizers = new ArrayList<>();

        // Create Login object
        Login loginSystem = new Login(organizers, participants);

        // Call home screen
        loginSystem.showHomeScreen();

        ArrayList<Participant> list=ParticipantCSV.loadParticipantCSV("resources/participants_sample.csv");
        System.out.println("number of participants loaded: "+list.size());

        //Participant participant=Login.login(list);
        //if(participant != null){
        //    participant.participantMenu();
        }

        //Survey.completeSurvey();

        // **** HAVE TO HANDLE EXCEPTIONS ****

 //   }
}
