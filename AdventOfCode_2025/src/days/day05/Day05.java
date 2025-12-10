package days.day05;

import days.DayChallenge;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unused")
public class Day05 extends DayChallenge {

    private List<Range> ranges;
    private List<Long> values;

    public Day05() {
        super();
    }

    @Override
    protected void runPartOne() {
        readAndParseInput();
        int result = 0;

        for (Long value : values) {
            if (isValueInAnyRange(value)) {
                result++;
            }
        }
        System.out.println("Result : " + result);
    }

    @Override
    protected void runPartTwo() {
        readAndParseInput();

        List<Range> combineRanges = combineRanges(ranges);

        long result = 0;
        for (Range range : combineRanges) {
            result += (range.getEnd() - range.getStart() + 1);
        }

        System.out.println("Result : " + result);
    }

    private List<Range> combineRanges(List<Range> rangeList) {
        if (rangeList.isEmpty()) return new ArrayList<>();

        List<Range> ordered = new ArrayList<>(rangeList);
        ordered.sort(Comparator.comparingLong(Range::getStart));

        List<Range> combined = new ArrayList<>();
        Range current = ordered.getFirst();

        for (int i = 1; i < ordered.size(); i++) {
            Range next = ordered.get(i);

            if (current.getEnd() >= next.getStart() - 1) {
                current = new Range(current.getStart(),
                        Math.max(current.getEnd(), next.getEnd()));
            } else {
                combined.add(current);
                current = next;
            }
        }

        combined.add(current);

        return combined;
    }

    private void readAndParseInput() {
        List<String> lines = readInputFile();
        ranges = new ArrayList<>();
        values = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            if (line.contains("-")) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    try {
                        long start = Long.parseLong(parts[0].trim());
                        long end = Long.parseLong(parts[1].trim());
                        ranges.add(new Range(start, end));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid range format: " + line);
                    }
                }
            } else {
                try {
                    long value = Long.parseLong(line.trim());
                    values.add(value);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid value format: " + line);
                }
            }
        }
    }

    private boolean isValueInAnyRange(long value) {
        for (Range range : ranges) {
            if (range.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
