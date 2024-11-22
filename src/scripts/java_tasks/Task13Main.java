public class Task13Main {
    public static void main(String[] args) {
        // Testowanie metody calculateSumOfEvens za pomocą TaskExecutor
        Integer[] inputs = {0}; // Niepotrzebne dane wejściowe, ponieważ metoda nie przyjmuje argumentów
        Integer[] expectedResults = {110};

        TaskExecutor.executeTest(inputs, expectedResults, input -> Result.calculateSumOfEvens());
    }
}
