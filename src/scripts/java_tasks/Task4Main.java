public class Task4Main {
    public static void main(String[] args) {
        Double[][] inputs = {
                {3.0, 5.0},
                {2.5, 4.3},
                {-7.0, 2.0},
                {10.1, 0.9},
                {100.0, 200.0}
        };

        Double[] expectedResults = {
                8.0,
                6.8,
                -5.0,
                11.0,
                300.0
        };

        // Użycie TaskExecutor do przeprowadzenia testów
        TaskExecutor.executeTest(inputs, expectedResults, Result::addTwoNumbers);
    }
}