package days.day04;

import days.DayChallenge;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Day04 extends DayChallenge {

    public Day04() {
        super(4);
    }

    @Override
    protected void runPartOne() {
        List<List<Integer>> uwu = readValuesMatrix();
        int result = 0;

        int rows = uwu.size();
        int cols = uwu.getFirst().size();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (uwu.get(i).get(j) == 0) continue; // Only check @ cells

                int sum = 0;

                for (int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        int ni = i + di;
                        int nj = j + dj;

                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                            sum += uwu.get(ni).get(nj);
                        }
                    }
                }

                if (sum < 5) {
                    result++;
                }
            }
        }

        System.out.println("Result: " + result);
    }

    @Override
    protected void runPartTwo() {
        List<List<Integer>> uwu = readValuesMatrix();
        int result = 0;
        int rows = uwu.size();
        int cols = uwu.getFirst().size();

        boolean foundAny = true;

        while (foundAny) {
            List<int[]> positionsToZero = new ArrayList<>();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (uwu.get(i).get(j) == 0) continue;

                    int sum = 0;

                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            int ni = i + di;
                            int nj = j + dj;

                            if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                                sum += uwu.get(ni).get(nj);
                            }
                        }
                    }

                    if (sum < 5) {
                        result++;
                        positionsToZero.add(new int[]{i, j});
                    }
                }
            }

            for (int[] pos : positionsToZero) {
                uwu.get(pos[0]).set(pos[1], 0);
            }

            foundAny = !positionsToZero.isEmpty();
        }

        System.out.println("Result: " + result);
    }


    private List<List<Integer>> readValuesMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        List<String> lines = readInputFile();

        for (String line : lines) {
            List<Integer> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                if(c == '@'){
                    row.add(1);
                }
                else{
                    row.add(0);
                }
            }
            matrix.add(row);
        }

        return matrix;
    }

}

