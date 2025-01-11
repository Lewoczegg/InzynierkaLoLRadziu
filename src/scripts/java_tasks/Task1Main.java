public class Task1Main {
    public static void main(String[] args) {
        Integer[] inputs = {
                42,
                -7,
                100
        };

        Integer[] expectedResults = {
                42,
                -7,
                100
        };


        TaskExecutor.executeTest(inputs, expectedResults, Result::defineInteger);
    }
}