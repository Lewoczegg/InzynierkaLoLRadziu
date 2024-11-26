public class Task8Main {
    public static void main(String[] args) {
        Integer[] inputs = {
                5,
                -3,
                0,
                12,
                -1
        };

        String[] expectedResults = {
                "5 is positive",
                "-3 is not positive",
                "0 is not positive",
                "12 is positive",
                "-1 is not positive"
        };

        TaskExecutor.<Integer, String>executeTest(inputs, expectedResults, Result::checkIfPositive);
    }
}