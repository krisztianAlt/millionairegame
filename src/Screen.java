import org.w3c.dom.ranges.Range;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

import static javafx.scene.input.KeyCode.T;

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

    public static void displayQuestion(String[] questionWithAnswers, String[] randomizedAnswers){
        Scanner scan = new Scanner(System.in);

        System.out.println("Question: " + questionWithAnswers[0]);
        System.out.println("1. "+ randomizedAnswers[0]);
        System.out.println("2. "+ randomizedAnswers[1]);
        System.out.println("3. "+ randomizedAnswers[2]);
        System.out.println("4. "+ randomizedAnswers[3]);
    }

    public static int getUserChoose() {
        Scanner select = new Scanner(System.in);
        System.out.println("Please select an answer: ");
        int option = 0;

        if (select.hasNextInt()) {
            option = select.nextInt();

            if (option == 1 || option == 2 || option == 3 || option == 4) {
                return option;
            }
        }

        return -1;
    }

    public static void displaySuccessMessage(){
        System.out.println("\nGreat, you proceed to next level!\n");
    }

}
