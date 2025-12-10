package days.day06;

import days.DayChallenge;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Day06 extends DayChallenge {

    public Day06() {
        super();
    }

    @Override
    protected void runPartOne() {
        Pair<List<List<Long>>, List<Character>> data = readValuesConverted();
        List<List<Long>> numberRows = data.getKey();
        List<Character> operators = data.getValue();

        long result = 0L;
        int numColumns = operators.size();

        for (int col = 0; col < numColumns; col++) {
            char operator = operators.get(col);
            long columnResult = numberRows.getFirst().get(col);

            for (int row = 1; row < numberRows.size(); row++) {
                long value = numberRows.get(row).get(col);

                if (operator == '+') {
                    columnResult += value;
                } else if (operator == '*') {
                    columnResult *= value;
                }
            }

            result += columnResult;
        }

        System.out.println("Result: " + result);
    }

    @Override
    protected void runPartTwo() {
        List<String> lines = readInputFile();
        List<Character> operators = new ArrayList<>();

        String operatorLine = lines.getLast();
        String[] operatorTokens = operatorLine.trim().split("\\s+");
        for (String token : operatorTokens) {
            operators.add(token.charAt(0));
        }

        List<String> numberLines = lines.subList(0, lines.size() - 1);
        List<Integer> columnStarts = calculateColumnStarts(operatorLine);
        List<Integer> columnEnds = calculateColumnEnds(operatorLine, columnStarts);

        long result = 0L;

        for (int col = 0; col < operators.size(); col++) {
            char operator = operators.get(col);
            int columnStart = columnStarts.get(col);
            int columnEnd = columnEnds.get(col);
            int columnWidth = columnEnd - columnStart + 1;

            List<Long> positionNumbers = new ArrayList<>();

            for (int pos = 0; pos < columnWidth; pos++) {
                StringBuilder combinedDigits = new StringBuilder();

                for (String line : numberLines) {
                    if (columnStart + pos < line.length()) {
                        char ch = line.charAt(columnStart + pos);
                        if (ch != ' ') {
                            combinedDigits.append(ch);
                        }
                    }
                }

                if (!combinedDigits.isEmpty()) {
                    positionNumbers.add(Long.parseLong(combinedDigits.toString()));
                }
            }

            if (!positionNumbers.isEmpty()) {
                long columnResult = positionNumbers.getFirst();
                for (int i = 1; i < positionNumbers.size(); i++) {
                    if (operator == '+') {
                        columnResult += positionNumbers.get(i);
                    } else if (operator == '*') {
                        columnResult *= positionNumbers.get(i);
                    }
                }
                result += columnResult;
            }
        }

        System.out.println("Result: " + result);
    }

    private List<Integer> calculateColumnStarts(String operatorLine) {
        List<Integer> starts = new ArrayList<>();
        int i = 0;

        while (i < operatorLine.length()) {
            char ch = operatorLine.charAt(i);
            if (ch == '+' || ch == '*') {
                starts.add(i);
                i++;
                while (i < operatorLine.length() && operatorLine.charAt(i) == ' ') {
                    i++;
                }
            } else {
                i++;
            }
        }

        return starts;
    }

    private List<Integer> calculateColumnEnds(String operatorLine, List<Integer> starts) {
        List<Integer> ends = new ArrayList<>();

        for (int i = 0; i < starts.size(); i++) {
            int start = starts.get(i);
            int end;

            if (i < starts.size() - 1) {
                end = starts.get(i + 1) - 1;
            } else {
                end = operatorLine.length() - 1;
            }

            ends.add(end);
        }

        return ends;
    }

    private Pair<List<List<Long>>, List<Character>> readValuesConverted() {
        List<List<Long>> numberRows = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        List<String> lines = readInputFile();

        for (String line : lines) {
            if (line.contains("+") || line.contains("*")) {
                String[] operatorTokens = line.trim().split("\\s+");
                for (String token : operatorTokens) {
                    operators.add(token.charAt(0));
                }
                break;
            }

            List<Long> row = new ArrayList<>();
            String[] numbers = line.trim().split("\\s+");
            for (String numberStr : numbers) {
                if (!numberStr.isEmpty()) {
                    row.add(Long.parseLong(numberStr));
                }
            }
            numberRows.add(row);
        }

        return new Pair<>(numberRows, operators);
    }
}
