package days.day03;

import days.DayChallenge;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Day03 extends DayChallenge {

    public Day03() {
        super(3);
    }

    @Override
    protected void runPartOne() {
        var matrix = readValuesMatrix();
        System.out.println("Part 1" + readValuesMatrix());
        long result = 0;

        for (List<Integer> row : matrix) {
            int maximum1 = 0;
            int posMax = -1;

            for (int index = 0; index < row.size() - 1; index++) {
                if (row.get(index) > maximum1) {
                    maximum1 = row.get(index);
                    posMax = index;
                }
            }

            int maximum2 = 0;
            for (int index = posMax + 1; index < row.size(); index++) {
                if (row.get(index) > maximum2) {
                    maximum2 = row.get(index);
                }
            }

            String iwishididjavascript = maximum1 + "" + maximum2;
            result += Integer.parseInt(iwishididjavascript);
        }

        System.out.println("Result: " + result);
    }

    @Override
    protected void runPartTwo() {
        var matrix = readValuesMatrix();
        long result = 0;

        for (List<Integer> row : matrix) {
            long maxNumber = findLargest12DigitNumber(row);
            result += maxNumber;
        }

        System.out.println("Result: " + result);
    }

    private long findLargest12DigitNumber(List<Integer> row) {

        StringBuilder result = new StringBuilder();
        int digitsNeeded = 12;
        int startIndex = 0;

        while (digitsNeeded > 0) {
            int maxDigit = -1;
            int maxIndex = -1;

            int searchEnd = row.size() - digitsNeeded + 1;

            for (int i = startIndex; i < searchEnd; i++) {
                if (row.get(i) > maxDigit) {
                    maxDigit = row.get(i);
                    maxIndex = i;
                }
            }

            result.append(maxDigit);
            startIndex = maxIndex + 1;
            digitsNeeded--;
        }

        return Long.parseLong(result.toString());
    }

    private List<List<Integer>> readValuesMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        List<String> lines = readInputFile();

        for (String line : lines) {
            List<Integer> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(Character.getNumericValue(c));
            }
            matrix.add(row);
        }

        return matrix;
    }

}

