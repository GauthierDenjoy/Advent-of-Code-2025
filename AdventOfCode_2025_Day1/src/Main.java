import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /* =====================================================================
       ==========  YOUR VERSION (simple add + wrap + zero counting)  ========
       ===================================================================== */

    // took me around 7 tries

    public static void runYourProgram() {
        List<Integer> values = readValuesConverted("src/input.txt");
        int counter = 50;
        int password = 0;

        System.out.println("Running YOUR version...\n");

        for (int value : values) {
            int oldCounter = counter;
            counter += value;

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

            password += crosses;

            if (counter > 0) {
                counter = counter % 100;
            } else if (counter < 0) {
                counter = -(Math.abs(counter) % 100);
            }
        }

        System.out.println("\nYour Program Result (password): " + password);
    }

    public static List<Integer> readValuesConverted(String filename) {
        List<Integer> values = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    char direction = line.charAt(0);
                    int number = Integer.parseInt(line.substring(1));
                    values.add(direction == 'L' ? -number : number);
                }
            }
        } catch (IOException e) { System.err.println(e.getMessage()); }

        return values;
    }


    /* =====================================================================
       =====================  CHATGPT VERSION  =============================
       ===================================================================== */

    // took him around 20 tries

    private List<String> input;

    public Main(List<String> input) { this.input = input; }

    private enum Direction { LEFT, RIGHT }

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


    /* =====================================================================
       =======================  MENU + ENTRY POINT  ========================
       ===================================================================== */

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Choose program:");
        System.out.println("1 = Your Program");
        System.out.println("2 = ChatGPT Program");
        System.out.print("Enter choice: ");

        int choice = scan.nextInt();
        System.out.println();

        if (choice == 1) {
            runYourProgram();
        }
        else if (choice == 2) {
            List<String> input = readRaw("src/input.txt");
            Main solver = new Main(input);

            System.out.println("Running ChatGPT Version...");
            System.out.println("Part One: " + solver.solvePartOne());
            System.out.println("Part Two: " + solver.solvePartTwo());
        }
        else {
            System.out.println("Invalid choice.");
        }
    }


    public static List<String> readRaw(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) lines.add(line);
        } catch (IOException e) { System.err.println(e.getMessage()); }
        return lines;
    }
}
