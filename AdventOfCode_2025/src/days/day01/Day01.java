package days.day01;

import days.DayChallenge;
import java.util.ArrayList;
import java.util.List;

public class Day01 extends DayChallenge {

    public Day01() {
        super(1);
    }

    @Override
    protected void displayMenu() {
        System.out.println("1 - 2nd part");
        System.out.print("Enter choice: ");
    }

    @Override
    protected void executeChoice(int choice) {
        switch (choice) {
            case 1 -> runYourVersion();
            default -> invalidChoice();
        }
    }

    /* =====================================================================
       ==========  YOUR VERSION (simple add + wrap + zero counting)  ========
       ===================================================================== */

    private void runYourVersion() {
        List<Integer> values = readValuesConverted();
        int counter = 50;
        int password = 0;


        for (int value : values) {
            int oldCounter = counter;
            counter += value;

            int crosses = getCrosses(oldCounter, counter);
            password += crosses;

            if (counter > 0) {
                counter = counter % 100;
            } else if (counter < 0) {
                counter = -(Math.abs(counter) % 100);
            }
        }

        System.out.println("\nResult: " + password);
    }

    private int getCrosses(int oldCounter, int counter) {
        int crosses = 0;

        if (oldCounter < counter) {
            int start = oldCounter + (100 - (oldCounter % 100 + 100) % 100);
            if (start == oldCounter) start += 100;

            for (int i = start; i <= counter; i += 100) {
                crosses++;
            }
        } else {
            int start = oldCounter - ((oldCounter % 100 + 100) % 100);
            if (start == oldCounter) start -= 100;

            for (int i = start; i >= counter; i -= 100) {
                crosses++;
            }
        }
        return crosses;
    }

    private List<Integer> readValuesConverted() {
        List<Integer> values = new ArrayList<>();
        List<String> lines = readInputFile();

        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty()) {
                char direction = line.charAt(0);
                int number = Integer.parseInt(line.substring(1));
                values.add(direction == 'L' ? -number : number);
            }
        }

        return values;
    }
}
