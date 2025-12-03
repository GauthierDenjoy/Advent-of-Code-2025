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

    protected DayChallenge(int dayNumber) {
        this.dayNumber = dayNumber;
        this.dayFolder = String.format("AdventOfCode_2025/src/days/day%02d/", dayNumber);
    }

    public void run() {
        System.out.println("=== Day " + dayNumber + " Solutions ===");
        displayMenu();

        int choice = scanner.nextInt();
        System.out.println();

        executeChoice(choice);
    }

    protected abstract void displayMenu();

    protected abstract void executeChoice(int choice);

    protected List<String> readInputFile(String filename) {
        List<String> lines = new ArrayList<>();
        String filepath = dayFolder + filename;
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

    protected List<String> readInputFile() {
        return readInputFile("input.txt");
    }

    protected void invalidChoice() {
        System.out.println("Invalid choice.");
    }
}
