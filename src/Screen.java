
import com.sun.deploy.util.StringUtils;

import java.util.*;


public class Screen {

    public static void clear() {
        System.out.print("\033[2J\033[1;1H");
    }

    public static int printMenu() {
        String[] menu = {"New Game", "High Scores", "Credits", "Exit"};
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i+1 + " - " + menu[i]);
        }
        return menu.length;
    }

    public static int selectMenu(ArrayList<Integer> validOptions) {
        Scanner select = new Scanner(System.in);
        System.out.println("Please select an option: ");
        int option = 0;


        if (select.hasNextInt()) {
            option = select.nextInt();

            if (validOptions.indexOf(option) != -1) {
                return option;
            }
        }

        return -1;
    }

    public static void displayQuestion(Game game, String[] questionWithAnswers, String[] randomizedAnswers){
        clear();

        System.out.println("WHO WANTS TO BE A MILLIONAIRE?");

        // print progress bar:
        String userName = game.getUserName();
        int currentLevel = game.getCurrentLevel();
        String plusSigns = "";
        for (int index = 1; index < currentLevel; index++) {
            plusSigns = plusSigns+"+";
        }
        String remainedLevels = "";
        for (int index=currentLevel; index<11;index++){
            remainedLevels = remainedLevels + "(" + index + ")";
        }
        System.out.println("\n" + userName + ": " + plusSigns + remainedLevels + "\n\n");

        // print question and answers:
        System.out.println("Question: " + questionWithAnswers[0] + "\n");
        // NEW STUFF
        for(int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ". " + randomizedAnswers[i]);
        }

        // print helpers:
        HashMap<String, Boolean> availableHelpers = game.getHasHelpers();
        String helpers = "";
        if (availableHelpers.get("half") == true){
            helpers = helpers + "50:50 (H)  ";
        }
        if (availableHelpers.get("poll") == true){
            helpers = helpers + "POLL (P)  ";
        }
        if (availableHelpers.get("expert") == true){
            helpers = helpers + "Expert (E)";
        }
        System.out.println("\n"+helpers);
        System.out.println("\nTake the money & run (T)");

    }

    public static String getUserChoose(Game game) {
        Scanner select = new Scanner(System.in);
        System.out.println("\nPlease select an answer: ");

        // construct the list of actual number buttons:
        List<Integer> numberButtons = new ArrayList<>();
        for (int numOfButton = 1; numOfButton < 5; numOfButton++){
            numberButtons.add(numOfButton-1, numOfButton);
        }

        // construct the list of actual helper buttons:
        HashMap<String, Boolean> availableHelpers = new HashMap<>();
        availableHelpers = game.getHasHelpers();
        List<String> helperButtons = new ArrayList<>();
        if (availableHelpers.get("half") == true){
            helperButtons.add("H");
        }
        if (availableHelpers.get("poll") == true){
            helperButtons.add("P");
        }
        if (availableHelpers.get("expert") == true){
            helperButtons.add("E");
        }
        helperButtons.add("T");

        // get the user's choice:
        if (select.hasNextInt()) {
            Integer option = select.nextInt();

            if (numberButtons.contains(option)) {
                return option.toString();
            }
        } else if(select.hasNextLine()){
            String option = select.nextLine().toUpperCase();

            if (helperButtons.contains(option)){
                return option;
            }
        }

        return "wrong input";
    }

    public static String getUserName() {

        String userName;

        Scanner name = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        userName = name.nextLine();

        return userName;

    }

    public static void displayMessages(String message){
        Scanner justOnePush = new Scanner(System.in);
        System.out.println("\n" + message + "\n");
        System.out.println("Please, press any button to continue.");
        if (justOnePush.hasNextLine()) {
            System.out.println("Great.");
        }
    }


}
