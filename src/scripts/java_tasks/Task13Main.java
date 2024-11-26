public class Task13Main {
    public static void main(String[] args) {
        Integer[] inputs = {0};
        Integer[] expectedResults = {110};

        TaskExecutor.executeTest(inputs, expectedResults, input -> Result.calculateSumOfEvens());
    }
}
