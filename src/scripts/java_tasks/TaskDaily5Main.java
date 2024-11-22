import java.io.*;
import java.util.*;

public class TaskDaily5Main {
    public static void main(String[] args) {
        // Dane wejściowe do testowania
        List<Map<String, Integer>> inputs = Arrays.asList(
                Map.of("Alice", 85, "Bob", 90, "Charlie", 78),
                Map.of("StudentA", 50, "StudentB", 60, "StudentC", 70),
                Map.of("X", 100, "Y", 100, "Z", 100)
        );

        // Oczekiwane wyniki po obliczeniu średniej wartości ocen
        Double[] expectedResults = {84.33, 60.0, 100.0};

        // Użycie TaskExecutor do przeprowadzenia testów na funkcji calculateAverage z klasy Result
        TaskExecutor.executeTest(inputs.toArray(new Map[0]), expectedResults, Result::calculateAverage);
    }
}
