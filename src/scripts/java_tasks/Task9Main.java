public class Task9Main {
    public static void main(String[] args) {
        Integer[][] inputs = {
                {10, 5},
                {3, 7},
                {4, 4},
                {-1, -5},
                {6, 6}
        };

        String[] expectedResults = {
                "10 is greater than 5",
                "3 is not greater than 7",
                "4 is not greater than 4",
                "-1 is greater than -5",
                "6 is not greater than 6"
        };

        // Użycie TaskExecutor do przeprowadzenia testów
        TaskExecutor.executeTest(inputs, expectedResults, Result::compareTwoNumbers);
    }
}
