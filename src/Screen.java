
import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Array;
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
        System.out.println("\nPlease select an option: ");
        int option = 0;


        if (select.hasNextInt()) {
            option = select.nextInt();

            if (validOptions.indexOf(option) != -1) {
                return option;
            }
        }

        return -1;
    }

    public static void displayProgressBar(Game game) {

        String userName = game.getUserName();
        int currentLevel = game.getCurrentLevel();
        String plusSigns = "";
        for (int index = 1; index < currentLevel; index++) {
            plusSigns = plusSigns+"(+)";
        }
        String remainedLevels = "";
        for (int index=currentLevel; index<11;index++){
            remainedLevels = remainedLevels + "(" + index + ")";
        }
        System.out.print("=====================================");
        System.out.println("\n" + userName + ": " + plusSigns + remainedLevels);
        System.out.println("=====================================\n");
    }

    public static void displayProgressBar(String userName, int currentLevel) {
        String plusSigns = "";
        for (int index = 1; index < currentLevel; index++) {
            plusSigns = plusSigns+"(+)";
        }
        String remainedLevels = "";
        for (int index=currentLevel; index<11;index++){
            remainedLevels = remainedLevels + "(" + index + ")";
        }
        System.out.print("=====================================");
        System.out.println("\n" + userName + ": " + plusSigns + remainedLevels);
        System.out.println("=====================================\n");
    }

    public static void displayQuestion(Game game, String[] questionWithAnswers, String[] randomizedAnswers){
        clear();
        displayHeader();
        displayProgressBar(game);

        // print question and answers:
        System.out.println("Question: " + questionWithAnswers[0] + "\n");
        // NEW STUFF
        for(int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ". " + randomizedAnswers[i]);
        }

        // print helpers:
        HashMap<String, Boolean> availableHelpers = game.getHasHelpers();
        String helpers = "";
        if (availableHelpers.get("half") == true ||
                availableHelpers.get("poll") == true ||
                availableHelpers.get("expert") == true) {
            System.out.println("\n=====================");
            System.out.println("Remaining life-lines:");
            System.out.print("=====================");
        }

        if (availableHelpers.get("half") == true){
            helpers = helpers + "50:50 (H)  ";
        }
        if (availableHelpers.get("poll") == true){
            helpers = helpers + "POLL (P)  ";
        }
        if (availableHelpers.get("expert") == true){
            helpers = helpers + "Expert (E)";
        }
        System.out.println("\n\n"+helpers);
        System.out.println("\n========================");
        System.out.println("Take the money & run (T)");
        System.out.println("========================\n");

    }

    public static String getUserChoose(Game game) {
        Scanner select = new Scanner(System.in);
        System.out.println("Please select an answer or use a life-line: ");

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
        displayHeader();
        System.out.println("Please enter your name: ");
        userName = name.nextLine();

        return userName;

    }

    public static void displayMessages(String message){
        System.out.println(message + "\n");
    }

    public static void confirmContinue() {
        Scanner justOnePush = new Scanner(System.in);
        System.out.println("Please, press ENTER to continue.");
        if (justOnePush.hasNextLine()) {
            clear();
        }
    }

    public static void displayRightAnswer(String question, String rightAnswer) {
        System.out.printf("Question: %s%nAnswer given: %s", question, rightAnswer);
        System.out.println("\n\n================");
        System.out.println("CORRECT ANSWER!");
        System.out.println("================");
        System.out.println("\nYou may proceed to the NEXT LEVEL!\n");
    }

    public static void displayWrongAnswer(String question, String rightAnswer, String givenAnswer, int checkPoint) {
        System.out.printf("Question: %s%nRight answer: %s", question, rightAnswer);
        System.out.printf("Given answer: %s", givenAnswer);
        System.out.println("\n\n================");
        System.out.println("WRONG ANSWER!");
        System.out.println("================");
        switch (checkPoint) {
            case 0:
                System.out.println("\nSorry, you have to go home with EMPTY hands!\n");
                break;
            case 3:
                System.out.println("\nYou won 25 coins.\n");
                break;
            case 7:
                System.out.println("\nYou won 500 coins.\n");
                break;
        }
    }

    public static void credits() {
        clear();
        displayHeader();

        System.out.println("\n==================");
        System.out.println("Developed by 4loop\n");
        System.out.println("The crew:\n\033[3mRegina Császár\nPéter Juhász\nMárk Kovács\nKrisztián Alt\033[0m");
        System.out.println("==================\n\n");

        confirmContinue();
    }

    public static void timer(int seconds) throws InterruptedException {
        long delay = seconds * 1000;
        String printedSeconds = "";
        do {
            printedSeconds += seconds + " ";
            clear();
            System.out.println(printedSeconds);
            Thread.sleep(1000);
            seconds = seconds - 1;
            delay = delay - 1000;
        }
        while (delay != 0);
        System.out.println("Time's Up!");
    }


    public static void displayHeader() {
        System.out.println("\nWHO WANTS TO BE A MILLIONAIRE?\n");
    }

    public static void printHighScores(List<ArrayList<String>> highScores) {
        displayHeader();
        System.out.println("\nHigh scores:\n");
        // System.out.println(highScores);

        int[] output = new int[highScores.size()];
        int upperLimit = 10;
        if (highScores.size() == 0) {
            System.out.println("The list is empty.");
        } else {
            if (highScores.size()<upperLimit){
                upperLimit = highScores.size();
            }

            for (int i = 0; i< upperLimit; i++){
                System.out.printf("%d. %s %s%n", (i+1), highScores.get(i).get(0), highScores.get(i).get(1));
            }
        }

        Scanner justOnePush = new Scanner(System.in);
        System.out.println("\n\n\nPlease, press any button to continue.");
        if (justOnePush.hasNextLine()) {
            clear();
        }

    }

    public static void endGame(String name, int point, int level) {
        clear();
        displayHeader();
        displayProgressBar(name, level);
        System.out.printf("Congratulations! %s, you have won %d coins.\n\n", name, point);
        confirmContinue();
    }

}
