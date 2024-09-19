public class Task4Main {
    public static void main(String[] args) {
        int[] testInputs = {0, 1, 3, 5, 7};
        int[] expectedOutputs = {1, 1, 6, 120, 5040};

        boolean allTestsPassed = true;
        for (int i = 0; i < testInputs.length; i++) {
            int result = Result.factorial(testInputs[i]);
            if (result == expectedOutputs[i]) {
                System.out.println("Test passed for input: " + testInputs[i]);
            } else {
                System.out.println("Test failed for input: " + testInputs[i] + ". Expected: " + expectedOutputs[i] + ", but got: " + result);
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
