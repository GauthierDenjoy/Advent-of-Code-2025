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
        System.out.println("1 - Your Version");
        System.out.println("2 - ChatGPT Version");
        System.out.print("Enter choice: ");
    }

    @Override
    protected void executeChoice(int choice) {
        switch (choice) {
            case 1 -> runYourVersion();
            case 2 -> runChatGPTVersion();
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

        System.out.println("Running YOUR version...\n");

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

        System.out.println("\nYour Program Result (password): " + password);
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

    /* =====================================================================
       =====================  CHATGPT VERSION  =============================
       ===================================================================== */

    private void runChatGPTVersion() {
        List<String> input = readInputFile();
        ChatGPTSolver solver = new ChatGPTSolver(input);

        System.out.println("Running ChatGPT Version...");
        System.out.println("Part One: " + solver.solvePartOne());
        System.out.println("Part Two: " + solver.solvePartTwo());
    }

    private record ChatGPTSolver(List<String> input) {

        private enum Direction {LEFT, RIGHT}

        private record Rotation(Direction direction, int distance) {
            static Rotation getFromLine(String line) {
                Direction dir = (line.charAt(0) == 'L') ? Direction.LEFT : Direction.RIGHT;
                return new Rotation(dir, Integer.parseInt(line.substring(1)));
            }
        }

        private int addLeft(int pos, int dist) {
            int newP = pos - dist;
            return ((newP % 100) + 100) % 100;
        }

        private int addRight(int pos, int dist) {
            return (pos + dist) % 100;
        }

        private int calcPosition(int pos, Rotation r) {
            return (r.direction == Direction.LEFT)
                    ? addLeft(pos, r.distance)
                    : addRight(pos, r.distance);
        }

        public int solvePartOne() {
            int counter = 0;
            int pos = 50;

            for (String line : input) {
                pos = calcPosition(pos, Rotation.getFromLine(line));
                if (pos == 0) counter++;
            }
            return counter;
        }

        private record RotationResult(int newPos, int zeroHits) {}

        public int solvePartTwo() {
            int total = 0;
            int pos = 50;

            for (String line : input) {
                Rotation r = Rotation.getFromLine(line);
                RotationResult rr = calcPositionAndZeroPasses(pos, r);
                total += rr.zeroHits;
                pos = rr.newPos;
            }
            return total;
        }

        private RotationResult calcPositionAndZeroPasses(int pos, Rotation r) {
            boolean left = r.direction == Direction.LEFT;
            int dist = r.distance;
            int zeros = 0;

            int finalPos = left ? addLeft(pos, dist) : addRight(pos, dist);

            if (left) {
                if (pos == 0) zeros = dist / 100;
                else if (dist >= pos) zeros = 1 + (dist - pos) / 100;
            } else {
                if (pos == 0) zeros = dist / 100;
                else {
                    int toZero = 100 - pos;
                    if (dist >= toZero) zeros = 1 + (dist - toZero) / 100;
                }
            }

            return new RotationResult(finalPos, zeros);
        }
    }
}
