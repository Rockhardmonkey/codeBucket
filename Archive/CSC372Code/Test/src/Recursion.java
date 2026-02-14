import java.util.Scanner;

public class Recursion {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter 5 numbers and I will provide the product:");
        int result = recursion(5);
        System.out.println("The product is " + result);
    }

    static int recursion(int n) {
        if (n == 1) {
            System.out.print("Last #:");
            return scanner.nextInt();
        } else {
            System.out.print("#:");
            return scanner.nextInt() * recursion(n - 1);

        }
    }
}
