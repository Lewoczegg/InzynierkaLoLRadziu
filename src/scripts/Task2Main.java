public class Task2Main {
    public static void main(String[] args) {
        String[] testInputs = {"hello", "world", "HotEl", "Java", ""};
        String[] expectedOutputs = {"olleh", "dlrow", "lEtoH", "avaJ", ""};

        boolean allTestsPassed = true;
        for (int i = 0; i < testInputs.length; i++) {
            String result = Result.reverseString(testInputs[i]);
            if (result.equals(expectedOutputs[i])) {
                System.out.println("Test passed for input: \"" + testInputs[i] + "\"");
            } else {
                System.out.println("Test failed for input: \"" + testInputs[i] + "\". Expected: \"" + expectedOutputs[i] + "\", but got: \"" + result + "\"");
                allTestsPassed = false;
            }
        }

        if (allTestsPassed) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
