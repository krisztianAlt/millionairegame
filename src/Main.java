import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;

public class Main {

    public static void processGame() {
        System.out.println("Hello from Logic class startGame method!");

        // Screen getUserName();
        /*game.setUserName = "gfmeaiofe";*/
        // Screen printMenu();
        String userName = "testgamer1";
        Game game = new Game();
        game.setUserName(userName);

        List maxLineNumber = DataManager.getMaxLineNumbers();
        System.out.println(maxLineNumber);
        System.out.println(game.getCurrentLevel());
        int level;

        boolean userInGame = true; // if game ends, we should change userInGame to false
        //System.out.println("RANDOM NUMBER: " + ThreadLocalRandom.current().nextInt(1, 5 + 1));
        //while (userInGame){
            boolean answerIsCorrect = true;
            level = game.getCurrentLevel();
            int upperLimit = (int) maxLineNumber.get(level-1);
            int randomQuestionNumber = ThreadLocalRandom.current().nextInt(1, upperLimit + 1);
            String[] questionWithAnswers = DataManager.readFromFile(game.getCurrentLevel(), randomQuestionNumber);
            System.out.println(Arrays.toString(questionWithAnswers));
            int answer = Screen.displayQuestion(questionWithAnswers);
            System.out.println("We get this answer: " + answer);

            if (answerIsCorrect == false){
                userInGame = false;
            }

        //}

    }

    public static void main(String[] args) {

        boolean invalidMenu = false;
        boolean exitGame = false;

        int menuLength = Screen.printMenu();
        ArrayList<Integer> validOptions = new ArrayList<Integer>();
        for (int i = 1; i <= menuLength; i++) {
            validOptions.add(i);
        }

        while (!exitGame) {

            Screen.clear();
            Screen.printMenu();

            if (invalidMenu) {
                System.out.println("Not valid MENU.");
                invalidMenu = false;
            }

            int option = Screen.selectMenu(validOptions);

            if (option == 1) {
                System.out.println("New Game");
            } else if (option == 2) {
                System.out.println("High Scores");
            } else if (option == 3) {
                System.out.println("Credits");
            } else if (option == 4) {
                System.out.println("Exiting Game");
                System.exit(0);
            } else if (option == -1) {
                invalidMenu = true;
            }
        }
    }
}
