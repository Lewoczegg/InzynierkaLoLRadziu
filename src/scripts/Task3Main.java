public class Task3Main {
    public static void main(String[] args) {
        int[] testInputs = {2, 4, 5, 9, 13, 17, 20, 23, 29, 31};
        boolean[] expectedOutputs = {true, false, true, false, true, true, false, true, true, true};

        boolean allTestsPassed = true;
        for (int i = 0; i < testInputs.length; i++) {
            boolean result = Result.isPrime(testInputs[i]);
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
