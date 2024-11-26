public class Task7Main {
    public static void main(String[] args) {
        Integer[] inputs = {
                4,
                7,
                0,
                -2,
                15
        };

        String[] expectedResults = {
                "4 is even",
                "7 is not even",
                "0 is even",
                "-2 is even",
                "15 is not even"
        };

        TaskExecutor.<Integer, String>executeTest(inputs, expectedResults, Result::checkIfEven);
    }
}