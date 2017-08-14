import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");

        // data reading:
        try {
            File questionFile = new File("data/questions_level_01.csv");
            Scanner fileContent = new Scanner(questionFile);
            while (fileContent.hasNextLine()){
                String line = fileContent.nextLine();
                String[] lineComponents = line.split(";");

                String question = lineComponents[0];
                System.out.println("\nQuestion: " + question);

                String goodAnswer = lineComponents[1];
                System.out.println("Good answer: " + goodAnswer);

                String badAnswer1 = lineComponents[2];
                System.out.println("Bad answer 1: " + badAnswer1);

                String badAnswer2 = lineComponents[3];
                System.out.println("Bad answer 2: " + badAnswer2);

                String badAnswer3 = lineComponents[4];
                System.out.println("Bad answer 3: " + badAnswer3);

                String category = lineComponents[5];
                System.out.println("Category: " + category);

            }
            fileContent.close();

        } catch (FileNotFoundException e) {
            System.out.println("Something is wrong. " + e);
        }
    }
}
