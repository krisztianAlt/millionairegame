import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

            // NEW STUFF
            for (int i = 0; i < 4; i++) {
                randomizedAnswers[i] = questionWithAnswers[i + 1];
            }

            Collections.shuffle(Arrays.asList(randomizedAnswers));

            Screen.displayQuestion(game, questionWithAnswers, randomizedAnswers);

            boolean answerIsNotValid = true;
            int answer = -1;
            while (answerIsNotValid){
                answer = Screen.getUserChoose();
                if (answer != -1) {
                    answerIsNotValid = false;
                }
            }

            // NEW STUFF
            int randomIndex = ThreadLocalRandom.current().nextInt(1, 4);
            int counter = 1;
            for (int i = 0; i < 4; i++) {
                if (randomizedAnswers[i] == questionWithAnswers[1]) {
                    continue;
                } else if (counter == randomIndex) {
                    counter++;
                } else {
                    randomizedAnswers[i] = "";
                    counter++;
                }

            }


            if (randomizedAnswers[answer-1] == questionWithAnswers[1]){
                level++;
                game.setCurrentLevel(level);
                if (level == 11){
                    Screen.displayMessages("You have won the game!");
                    userInGame = false;
                } else {
                    Screen.displayMessages("Great, you proceed to next level!");
                }
            } else {
                Screen.displayMessages("You have failed. Try again.");
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
