package days;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Day0X extends DayChallenge {

    public Day0X() {
        super();
    }

    @Override
    protected void runPartOne() {
        int result = 0;
        System.out.println("Result: " + result);
    }

    @Override
    protected void runPartTwo() {
        int result = 0;
        System.out.println("Result: " + result);
    }

    private List<List<Integer>> readValuesMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        List<String> lines = readInputFile();

        for (String line : lines) {
        //todo
        }

        return matrix;
    }

}

