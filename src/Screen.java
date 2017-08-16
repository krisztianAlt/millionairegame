import java.util.ArrayList;
import java.util.Scanner;

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

    public static int displayQuestion(String[] questionWithAnswers){
        Scanner scan = new Scanner(System.in);

        System.out.println("Question: " + questionWithAnswers[0]);
        System.out.println("1. "+ questionWithAnswers[1]);
        System.out.println("2. "+ questionWithAnswers[2]);
        System.out.println("3. "+ questionWithAnswers[3]);
        System.out.println("4. "+ questionWithAnswers[4]);

        int answer = scan.nextInt();

        return answer;
    }
}
