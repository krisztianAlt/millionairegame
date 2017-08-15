import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class DataManager {

    private static String filePath = "data/";
    private static String fileName = "questions_level_";
    private static String fileExtension = ".csv";


    public static List getMaxLineNumbers() {
        List<Integer> maxLineNumbers = new ArrayList<>();

        for (int index = 1; index < 11; index++) {
            int lineCounter = 0;
            try {
                File questionFile = new File( filePath + fileName + index + fileExtension);
                Scanner fileContent = new Scanner(questionFile);


                while (fileContent.hasNextLine()) {
                    String line = fileContent.nextLine();
                    lineCounter++;
                }

                maxLineNumbers.add(lineCounter);

            }
            catch (FileNotFoundException error) {
                System.out.println("File not found: " + error);
            }
        }

        return maxLineNumbers;
    }

    public static String[] readFromFile(int level, int lineNumber) {

        String[] splittedLine = {};
        try {
            File questionFile = new File( filePath + fileName + level + fileExtension);
            Scanner fileContent = new Scanner(questionFile);

            int lineCounter = 0;
            while (fileContent.hasNextLine()) {
                String line = fileContent.nextLine();
                if (lineCounter == lineNumber - 1) {
                    splittedLine = line.split(";");
                }
                lineCounter++;
            }
        }
        catch (FileNotFoundException error) {
            System.out.println("File not found: " + error);
        }
        return splittedLine;
    }

}
