import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Main {

    public static String[] halveAnswers(String[] randomizedAnswers, String rightAnswer) {
        int randomIndex = ThreadLocalRandom.current().nextInt(1, 4);
        int counter = 1;
        for (int i = 0; i < 4; i++) {
            if (randomizedAnswers[i] == rightAnswer) {
                continue;
            } else if (counter == randomIndex) {
                counter++;
            } else {
                randomizedAnswers[i] = "";
                counter++;
            }

        }

        return randomizedAnswers;
    }


    public static void processGame() {
        Game game = new Game();

        String userName = retrieveUserName();
        game.setUserName(userName);

        List maxLineNumber = DataManager.getMaxLineNumbers();

        int level = -1;

        String[] questionWithAnswers = new String[6];
        String[] randomizedAnswers = new String[4];
        boolean nextQuestionIsNotNeeded = true;

        boolean userInGame = true; // if game ends, we should change userInGame to false
        while (userInGame){
            if (nextQuestionIsNotNeeded){

                level = game.getCurrentLevel();
                int upperLimit = (int) maxLineNumber.get(level-1);

                int randomQuestionNumber = ThreadLocalRandom.current().nextInt(1, upperLimit + 1);

                questionWithAnswers = DataManager.readFromFile(game.getCurrentLevel(), randomQuestionNumber);


                // NEW STUFF
                for (int i = 0; i < 4; i++) {
                    randomizedAnswers[i] = questionWithAnswers[i + 1];
                }

                Collections.shuffle(Arrays.asList(randomizedAnswers));

                nextQuestionIsNotNeeded = false;
                Screen.displayQuestion(game, questionWithAnswers, randomizedAnswers);
            }

            boolean answerIsNotValid = true;
            String answer = "wrong input";
            while (answerIsNotValid){
                answer = Screen.getUserChoose(game);
                if (answer != "wrong input") {
                    answerIsNotValid = false;
                }
            }


            switch (answer){
                case "H":
                    randomizedAnswers = halveAnswers(randomizedAnswers, questionWithAnswers[1]);
                    game.setHasHelpers("half", false);
                    System.out.println("RANDIMOZED WITH TWO HIDDEN ANSWER: " + Arrays.toString(randomizedAnswers));
                    Screen.displayQuestion(game, questionWithAnswers, randomizedAnswers);
                    break;
                case "P":
                    game.setHasHelpers("poll", false);
                    Screen.displayQuestion(game, questionWithAnswers, randomizedAnswers);
                    break;
                case "E":
                    game.setHasHelpers("expert", false);

                    // __TODO__: add confirm message to start! Get Input from keyboard;
                    try {
                        Screen.timer(10);
                    }
                    catch (InterruptedException e) {
                        Screen.displayMessages("Time is over!");
                    }

                    Screen.displayQuestion(game, questionWithAnswers, randomizedAnswers);
                    break;
                case "T":
                    Screen.displayMessages("You are coward, but it's not problem, thanks for playing, really.");
                    Screen.confirmContinue();
                    userInGame = false;
                    break;
                default:
                    if (randomizedAnswers[Integer.parseInt(answer)-1] == questionWithAnswers[1]){
                        // set checkpoint:
                        if (level == 3){
                            game.setCheckPoint(3);
                        } else if (level == 7){
                            game.setCheckPoint(7);
                        }

                        level++;
                        game.setCurrentLevel(level);
                        if (level == 11){
                            Screen.displayMessages("You have won the game!");
                            Screen.confirmContinue();
                            userInGame = false;
                        } else {
                            nextQuestionIsNotNeeded = true;
                            Screen.clear();
                            Screen.displayHeader();
                            Screen.displayProgressBar(game);
                            Screen.displayRightAnswer(questionWithAnswers[0], questionWithAnswers[1]);
                            Screen.confirmContinue();
                        }
                    } else {
                        Screen.displayMessages("Sorry, wrong answer. Better luck next time!");
                        Screen.confirmContinue();
                        userInGame = false;
                    }
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


        while (!exitGame) {
            Screen.clear();

            Screen.displayHeader();
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
                // System.out.println("Credits");
                Screen.credits();
            } else if (option == 4) {
                System.out.println("Exiting Game");
                System.exit(0);
            } else if (option == -1) {
                invalidMenu = true;
            }
        }
    }
}
