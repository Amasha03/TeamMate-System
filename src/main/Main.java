package main;
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


        //concurrency.processSurveys();

        // Call home screen
        loginSystem.showHomeScreen();






    }

}
