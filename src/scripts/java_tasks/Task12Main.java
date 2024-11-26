public class Task12Main {
    public static void main(String[] args) {
        Integer[][] inputs = {
                {1, 2, 3, 4, 5},
                {-10, -3, -7, -2},
                {0, 0, 0},
                {42},
                {100, 200, 300, 400}
        };

        Integer[] expectedResults = {
                5,
                -2,
                0,
                42,
                400
        };
        TaskExecutor.executeTest(inputs, expectedResults, Result::findLargestElement);
    }
}