public class Task5Main {
    public static void main(String[] args) {
        Double[][] inputs = {
                {4.0, 5.0},
                {2.5, 3.0},
                {-3.0, -2.0},
                {7.0, 0.0},
                {100.0, 0.5}
        };

        Double[] expectedResults = {
                20.0,
                7.5,
                6.0,
                0.0,
                50.0
        };

        TaskExecutor.executeTest(inputs, expectedResults, Result::multiplyTwoNumbers);
    }
}
