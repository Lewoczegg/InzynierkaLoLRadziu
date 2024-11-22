public class Task6Main {
    public static void main(String[] args) {
        Double[][] inputs = {
                {10.0, 3.0},
                {7.0, 2.0},
                {-8.0, 3.0},
                {9.0, 4.0},
                {100.0, 6.0}
        };

        Integer[] expectedResults = {
                3,
                4,
                -3,
                2,
                17
        };

        // Użycie TaskExecutor do przeprowadzenia testów
        TaskExecutor.executeTest(inputs, expectedResults, Result::divideAndRound);
    }
}