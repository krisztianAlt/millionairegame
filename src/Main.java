import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("\033[2J\033[1;1H");

        printMenu();

        Scanner select = new Scanner(System.in);
        int option = 0;
        System.out.println("Please select an option: ");

        boolean notValidOption = true;
        while (notValidOption) {

            if (select.hasNextInt()) {
                option = select.nextInt();
                notValidOption = false;
            } else {
                // System.out.print(String.format("\033[%dA", 1));
                System.out.print("\033[2J\033[1;1H");

                // System.out.print(String.format("\033[%dA", 6));

                printMenu();
                System.out.println("Invalid option. Please select menu again.");
                select.next();
            }


            /*
            try {
                int option = select.nextInt();
                notValidOption = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please select menu again.");
            }
            */
        }
        System.out.println(option);

    }

    public static void printMenu() {
        String[] menu = {"New Game", "High Score", "Credits", "Exit"};
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i+1 + " - " + menu[i]);
        }
    }
}
