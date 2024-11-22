public class Task16Main {
    public static void main(String[] args) {
        // Testowanie metody sumTwoNumbers za pomocą TaskExecutor
        Integer[][] inputs = {{3, 5}, {-2, 10}, {0, 0}, {100, 200}, {-50, -70}};
        Integer[] expectedResults = {8, 8, 0, 300, -120};

        TaskExecutor.executeTest(inputs, expectedResults, Result::sumTwoNumbers);
    }
}