import java.util.ArrayList;

public class Main {

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
