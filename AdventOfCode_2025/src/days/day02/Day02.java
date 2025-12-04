package days.day02;

import days.DayChallenge;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Day02 extends DayChallenge {

    public Day02() {
        super(2);
    }

    @Override
    protected void runPartOne() {
        List<Long> values = readValuesConverted();
        List<String> stringValues = values.stream()
                .map(String::valueOf)
                .toList();
        long counter = 0;
        for (int i = 0; i < stringValues.size(); i++) {
            String stringValue = stringValues.get(i);
            int mid = stringValue.length() / 2;
            String firstHalf = stringValue.substring(0, mid);
            String secondHalf = stringValue.substring(mid);

            if (firstHalf.equals(secondHalf)) {
                System.out.println("String found: " + stringValue);
                counter += values.get(i);
            }
        }

        System.out.println("Result : " + counter);

    }

    @Override
    protected void runPartTwo() {
        List<Long> values = readValuesConverted();
        List<String> stringValues = values.stream()
                .map(String::valueOf)
                .toList();
        long counter = 0;

        for (int i = 0; i < stringValues.size(); i++) {
            String stringValue = stringValues.get(i);

            if (isInvalidId(stringValue)) {
                System.out.println("String found: " + stringValue);
                counter += values.get(i);
            }
        }

        System.out.println("Result: " + counter);
    }

    private boolean isInvalidId(String id) {
        int length = id.length();

        for (int len = 1; len <= length / 2; len++) {
            if (length % len == 0) {
                String pattern = id.substring(0, len);
                boolean dup = true;

                for (int j = len; j < length; j += len) {
                    String segment = id.substring(j, j + len);
                    if (!segment.equals(pattern)) {
                        dup = false;
                        break;
                    }
                }

                if (dup && length / len >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

    private List<Long> readValuesConverted() {
        List<Long> values = new ArrayList<>();
        List<String> lines = readInputFile();

        for (String line : lines) {
            String[] ranges = line.split(",");
            for (String range : ranges) {
                String[] parts = range.trim().split("-");
                if (parts.length == 2) {
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    for (long i = start; i <= end; i++) {
                        values.add(i);
                    }
                }
            }
        }

        return values;
    }
}
