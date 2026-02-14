public class captureGradeStats {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int MAX_ATTEMPTS = 100;

        int TOTAL = 0;
        int attempts = 0;
        System.out.print("How many grades will you enter? ");
        while (attempts < MAX_ATTEMPTS) {
            if (sc.hasNextInt()) {
                TOTAL = sc.nextInt();
                if (TOTAL > 0) break;
                System.out.print("Please enter a positive integer: ");
            } else {
                String bad = sc.next();
                System.out.print("Invalid input: '" + bad + "'. Enter a positive integer: ");
            }
            attempts++;
        }

        if (TOTAL <= 0) {
            System.out.println("No valid number of grades entered. Exiting.");
            return;
        }

        int count = 0;
        attempts = 0;
        double sum = 0.0;
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;

        System.out.println("Enter " + TOTAL + " grades (floating-point):");
        while (count < TOTAL && attempts < MAX_ATTEMPTS) {
            System.out.print("Grade " + (count + 1) + ": ");
            if (sc.hasNextDouble()) {
                double g = sc.nextDouble();
                sum += g;
                if (g > max) max = g;
                if (g < min) min = g;
                count++;
            } else {
                String bad = sc.next();
                System.out.println("Invalid input: '" + bad + "'. Please enter a number.");
            }
            attempts++;
        }

        if (count == 0) {
            System.out.println("No valid grades entered.");
        } else {
            if (count < TOTAL) {
                System.out.println("Maximum attempts reached; proceeding with " + count + " valid grade(s).");
            }
            double average = sum / count;
            System.out.printf("Average: %.2f%n", average);
            System.out.printf("Maximum: %.2f%n", max);
            System.out.printf("Minimum: %.2f%n", min);
        }
    }
}
