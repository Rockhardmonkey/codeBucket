import java.util.Random;
import java.util.Scanner;

public class getWeeklyTemperature {
    public static void main(String[] args) {
        int weeks = 52;
        int daysPerWeek = 7;
        int[][] weeklyTemperatures = new int[weeks][daysPerWeek];

        // Example: fill with sample temperatures (50-100°F)
        Random rand = new Random();
        for (int w = 0; w < weeks; w++) {
            for (int d = 0; d < daysPerWeek; d++) {
                weeklyTemperatures[w][d] = 50 + rand.nextInt(51);
            }
        }

        Scanner sc = new Scanner(System.in);
        outer:
        while (true) {
            System.out.print("Enter 'w' for week avg, 'd' for day info, or 'q' to quit: ");
            String choice = sc.nextLine().trim().toLowerCase();
            if (choice.isEmpty()) {
                System.out.println("No input entered. Please try again.");
                continue;
            }
            if (choice.startsWith("q")) {
                System.out.println("Exiting.");
                break;
            }
            if (choice.startsWith("w")) {
                Integer weekNum = promptIntInRange(sc, "Enter week number (1-" + weeks + ") or 'q' to quit: ", 1, weeks);
                if (weekNum == null) break;
                int wi = weekNum - 1;
                int sum = 0;
                for (int d = 0; d < daysPerWeek; d++) sum += weeklyTemperatures[wi][d];
                double average = sum / (double) daysPerWeek;
                System.out.printf("Average temperature for week %d: %.2f°F%n", weekNum, average);
            } else if (choice.startsWith("d")) {
                Integer weekNum = promptIntInRange(sc, "Enter week number (1-" + weeks + ") or 'q' to quit: ", 1, weeks);
                if (weekNum == null) break;
                Integer dayNum = promptIntInRange(sc, "Enter day number (1-" + daysPerWeek + ") or 'q' to quit: ", 1, daysPerWeek);
                if (dayNum == null) break;
                int temp = weeklyTemperatures[weekNum - 1][dayNum - 1];
                System.out.println("Week " + weekNum + " Day " + dayNum + ": " + temp + "°F");
            } else {
                System.out.println("Invalid choice. Please enter 'w', 'd', or 'q'.");
                continue;
            }

            // Ask whether to continue
            while (true) {
                System.out.print("Perform another query? (y/n): ");
                String again = sc.nextLine().trim().toLowerCase();
                if (again.startsWith("y")) break; // outer loop continues
                if (again.startsWith("n") || again.startsWith("q")) break outer;
                System.out.println("Please answer 'y' or 'n'.");
            }
        }
        sc.close();
    }

    // Prompts until valid integer in range or user types q/quit -> returns null
    private static Integer promptIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim().toLowerCase();
            if (line.isEmpty()) {
                System.out.println("No input entered. Please try again.");
                continue;
            }
            if (line.equals("q") || line.equals("quit")) {
                return null;
            }
            try {
                int val = Integer.parseInt(line);
                if (val < min || val > max) {
                    System.out.println("Value out of range. Expected between " + min + " and " + max + ".");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer or 'q' to quit.");
            }
        }
    }
}
