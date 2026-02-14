public class recursionVsIteration {
    
    // Recursive approach - calculates factorial
    public static int factorialRecursive(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }
    
    // Iterative approach - calculates factorial
    public static int factorialIterative(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static void main(String[] args) {
        int num = 5;
        
        System.out.println("Factorial of " + num + ":");
        System.out.println("Recursive: " + factorialRecursive(num));
        System.out.println("Iterative: " + factorialIterative(num));
    }
}