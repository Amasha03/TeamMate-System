import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Participant participant=Login.login();
        if(participant != null){
            participant.participantMenu();
        }

        Survey.completeSurvey();

        // **** HAVE TO HANDLE EXCEPTIONS ****



    }
}
