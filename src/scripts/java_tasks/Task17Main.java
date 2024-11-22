import java.util.*;

public class Task17Main {
    public static void main(String[] args) {
        // Przygotowanie danych wejściowych i oczekiwanych wyników
        List<Integer>[] inputs = new List[]{
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(-10, -3, -7, -2),
                Arrays.asList(0, 0, 0),
                Arrays.asList(42),
                Arrays.asList(-5, -1, -20)
        };

        Integer[] expectedResults = {5, -2, 0, 42, -1};

        // Użycie TaskExecutor do przeprowadzenia testów na funkcji findLargestInList z klasy Result
        TaskExecutor.executeTest(inputs, expectedResults, Result::findLargestInList);
    }
}
