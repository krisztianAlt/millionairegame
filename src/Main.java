import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        //System.out.println("Hello");
//        List maxLineNumber = DataManager.getMaxLineNumbers();
//        System.out.println(maxLineNumber);
//
//        String[] questionWithAnswers = DataManager.readFromFile(5, 3);
//        System.out.println(Arrays.toString(questionWithAnswers));

        processGame();

    }
}
