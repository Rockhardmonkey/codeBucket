import java.util.Scanner;

public class multipleChoice {
    public static void main (String[] args) {
    
String question = ("What is the capital of New Guinea?");
String choiceOne = ("papa");
String choiceTwo = ("n");
String choiceThree = ("some dude named geoff");

String correctAnswer = choiceTwo;

        System.out.println(question);
        System.out.println(choiceOne);
        System.out.println(choiceTwo);
        System.out.println(choiceThree);
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.next();

        if(correctAnswer.equals(input.toLowerCase())) {
            System.out.println("Whoo! your the Best!!!");
        } else {
           System.out.println("Close the actual answer is "+correctAnswer); 
        }

 }
}