public class Task1Main {
    public static void main(String[] args) {
        int[][] inputs = {
                {1, 2},
                {3, 4},
                {10, 20}
        };

        int[] expectedResults = {
                3,
                7,
                30
        };

        for (int i = 0; i < inputs.length; i++) {
            int[] input = inputs[i];
            int expectedResult = expectedResults[i];
            int result = Result.sum(input[0], input[1]);

            if (result == expectedResult) {
                System.out.println("Test passed for input: " + input[0] + " and " + input[1] + ". Result: " + result);
            } else {
                System.out.println("Test failed for input: " + input[0] + " and " + input[1] + ". Expected: " + expectedResult + ", but got: " + result);
            }
        }
//        System.out.println("..........ANSWER..........");

    }
}
