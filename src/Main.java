import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Participant> participants = CSVFileHandler.loadParticipantCSV("resources/participants_sample.csv");
        ArrayList<Organizer> organizers =CSVFileHandler.getOrganizers("resources/organizer_sample.csv");

        for(Organizer o : organizers){
            o.participants=participants;
        }
        // Create Login object
        Login loginSystem = new Login(organizers, participants);

        // Call home screen
        loginSystem.showHomeScreen();

        //concurrency
        Concurrency concurrency = new Concurrency(participants);
        concurrency.processSurveys();
        concurrency.formTeams(participants,5);
        concurrency.shutdown();

        //TeamFormation teamFormation=new TeamFormation();
        //Organizer.showMenu().;

/**
        int teamNum=1;
        for(Team t : TeamFormation.teams){
            System.out.println(teamNum+" "+teamNum++);
            System.out.println(t);
        }**/
    }

}
