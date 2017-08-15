import java.util.Scanner;


public class Screen {

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
