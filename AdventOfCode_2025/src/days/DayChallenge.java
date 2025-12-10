package days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class DayChallenge {
    protected final Scanner scanner = new Scanner(System.in);
    protected final int dayNumber;
    protected final String dayFolder;

    protected DayChallenge() {
        this.dayNumber = extractDayNumberFromSubclass();
        this.dayFolder = String.format("AdventOfCode_2025/src/days/day%02d/", dayNumber);
    }

    private int extractDayNumberFromSubclass() {
        String className = this.getClass().getSimpleName();
        String numberStr = className.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberStr);
    }

    public void run() {
        System.out.println("=== Day " + dayNumber + " Solutions ===");
        displayMenu();

        int choice = scanner.nextInt();
        System.out.println();

        executeChoice(choice);
    }

    protected void displayMenu() {
        System.out.println("1 - Part 1");
        System.out.println("2 - Part 2");
        System.out.print("Enter choice: ");
    }

    protected void executeChoice(int choice) {
        switch (choice) {
            case 1 -> runPartOne();
            case 2 -> runPartTwo();
            default -> System.out.println("Invalid choice.");

        }
    }

    protected abstract void runPartOne();

    protected abstract void runPartTwo();

    protected List<String> readInputFile() {
        List<String> lines = new ArrayList<>();
        String filepath = dayFolder + "input.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file '" + filepath + "': " + e.getMessage());
        }
        return lines;
    }
}
