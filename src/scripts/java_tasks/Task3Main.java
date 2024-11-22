public class Task3Main {
    public static void main(String[] args) {
        Integer[] inputs = {
                42,
                -7,
                0
        };

        String[] expectedOutputs = {
                "42",
                "-7",
                "0"
        };


        TaskExecutor.<Integer, String>executeTest(inputs, expectedOutputs, Result::displayValue);
    }
}
