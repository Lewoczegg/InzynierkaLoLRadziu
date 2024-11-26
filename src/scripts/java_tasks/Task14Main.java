public class Task14Main {
    public static void main(String[] args) {
        Integer[] inputs = {5, 10, 1, 0, -5};
        Integer[] expectedResults = {15, 55, 1, 0, 0};

        TaskExecutor.executeTest(inputs, expectedResults, Result::sumNumbersUpToN);
    }
}
