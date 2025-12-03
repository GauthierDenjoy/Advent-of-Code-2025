import days.*;
import days.day01.Day01;
import days.day02.Day02;
import days.day03.Day03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Advent of Code Runner =====");
            System.out.println("Select a day (0 to exit):\n");

            for (int i = 1; i <= 25; i++) {
                System.out.printf("%2d ", i);
                if (i % 5 == 0) System.out.println();
            }

            System.out.println("\n\n0 - Exit");
            System.out.print("Choice: ");

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
            case 3 -> new Day03();
            default -> null;
        };
    }
}
