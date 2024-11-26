public class Task15Main {
    public static void main(String[] args) {
        int[][] inputs = {
                {1, 2, 3, 14, 8, 9},
                {5, 9, 13, 21, 4, 7},
                {8, 10, 15, 28, 32, 45},
                {1, 6, 12, 13, 49},
                {3, 4, 10, 20, 35},
                {50, 44, 42, 7}
        };
        Integer[] expectedResults = {14, 21, 28, 49, 35, 42};

        TaskExecutor.executeTest(inputs, expectedResults, input -> Result.findNumberDivisibleBy7(input));
    }
}
