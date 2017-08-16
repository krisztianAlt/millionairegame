import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;

public class Main {

    public static void processGame() {
        Game game = new Game();

        String userName = retrieveUserName();
        game.setUserName(userName);

        List maxLineNumber = DataManager.getMaxLineNumbers();

        int level;

        boolean userInGame = true; // if game ends, we should change userInGame to false
        while (userInGame){

            level = game.getCurrentLevel();
            int upperLimit = (int) maxLineNumber.get(level-1);

            int randomQuestionNumber = ThreadLocalRandom.current().nextInt(1, upperLimit + 1);

            String[] questionWithAnswers = DataManager.readFromFile(game.getCurrentLevel(), randomQuestionNumber);


            String[] randomizedAnswers = new String[4];
            randomizedAnswers[0] = questionWithAnswers[1];
            randomizedAnswers[1] = questionWithAnswers[2];
            randomizedAnswers[2] = questionWithAnswers[3];
            randomizedAnswers[3] = questionWithAnswers[4];
            Collections.shuffle(Arrays.asList(randomizedAnswers));
            // System.out.println("RANDIMOZED ANSWERS: " + Arrays.toString(randomizedAnswers));

            Screen.displayQuestion(questionWithAnswers, randomizedAnswers);

            boolean answerIsNotValid = true;
            int answer = -1;
            while (answerIsNotValid){
                answer = Screen.getUserChoose();
                if (answer != -1) {
                    answerIsNotValid = false;
                }
            }

            if (randomizedAnswers[answer-1] == questionWithAnswers[1]){
                level++;
                game.setCurrentLevel(level);
                if (level == 11){
                    System.out.println("You have won the game!");
                    userInGame = false;
                } else {
                    Screen.displaySuccessMessage();
                    // System.out.println("Great, you proceed to next level!");
                }
            } else {
                System.out.println("You have failed. Try again.");
                userInGame = false;
            }

        }

    }



    public static String retrieveUserName() {

        Boolean status = true;
        String userName = null;

        do {
            userName = Screen.getUserName();
            if (userName.length() > 3 && userName.length() < 10) {
                status = false;
            }
        } while (status);

        return userName;
    }

    public static void main(String[] args) {

        boolean invalidMenu = false;
        boolean exitGame = false;

        int menuLength = Screen.printMenu();

        ArrayList<Integer> validOptions = new ArrayList<Integer>();
        for (int i = 1; i <= menuLength; i++) {
            validOptions.add(i);
        }

        Screen.clear();

        while (!exitGame) {

            Screen.printMenu();

            if (invalidMenu) {
                System.out.println("Not valid MENU.");
                invalidMenu = false;
            }

            int option = Screen.selectMenu(validOptions);

            Screen.clear();
            if (option == 1) {
                processGame();
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
