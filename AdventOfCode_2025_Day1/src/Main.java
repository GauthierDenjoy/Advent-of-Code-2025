import days.*;
import days.day01.Day01;
import days.day02.Day02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Advent of Code Runner ===");
            System.out.println("Select a day to run (or 0 to exit):");
            System.out.println("1 - Day 1");
            System.out.println("2 - Day 2");
            System.out.print("\nEnter choice: ");

            int choice = scanner.nextInt();
            System.out.println();

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            DayChallenge day = getDayChallenge(choice);
            if (day != null) {
                day.run();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static DayChallenge getDayChallenge(int day) {
        return switch (day) {
            case 1 -> new Day01();
            case 2 -> new Day02();
            default -> null;
        };
    }
}
