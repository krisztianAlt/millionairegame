import java.io.*;
import java.util.*;


public class DataManager {

    private static String filePath = "../../../data/";
    private static String fileName = "questions_level_";
    private static String highScoreFileName = "highscores";
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

    public static List<ArrayList<String>> getHighScores() {
        // TreeMap<Integer, String> highScores = new TreeMap<Integer, String>();
        List<ArrayList<String>> highScores = new ArrayList<>();

        try {
            File highScoresFile = new File( filePath + highScoreFileName + fileExtension);
            Scanner fileContent = new Scanner(highScoresFile);

            String[] splittedLine = {};
            while (fileContent.hasNextLine()) {
                String line = fileContent.nextLine();
                splittedLine = line.split(";");
                // highScores.put(Integer.parseInt(splittedLine[1]), splittedLine[0]);
                ArrayList<String> nextUserData = new ArrayList<>();
                nextUserData.add(splittedLine[0]);
                nextUserData.add(splittedLine[1]);

                highScores.add(nextUserData);
            }
            // highScores.sort((p1,p2)->p1.get(1).compareTo(p2.get(1)));
            highScores = sortHighScore((ArrayList<ArrayList<String>>) highScores);
            // Collections.reverse(highScores);

        }
        catch (FileNotFoundException error) {
            System.out.println("File not found: " + error);
        }

        return highScores;
    }

    public static void saveResult(String name, int point) throws IOException {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + highScoreFileName + fileExtension, true));
            writer.append(name + ";" + point + "\n");
            writer.close();

        } catch (FileNotFoundException error) {
            System.out.println("File not found: " + error);
        }

    }

    public static List sortHighScore(ArrayList<ArrayList<String>> inputList) {
        for (int i = 0; i < inputList.size()-1; i++) {
            for (int j = 0; j < inputList.size()-1; j++) {
                if (Integer.parseInt(inputList.get(j).get(1)) < Integer.parseInt(inputList.get(j + 1).get(1))) {
                    ArrayList temp = inputList.get(j);
                    inputList.set(j, inputList.get(j+1));
                    inputList.set(j+1, temp);
                }

            }

        }

        return inputList;
    }

}
