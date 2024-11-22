import java.io.*;
import java.util.*;

public class TaskDaily4Main {
    public static void main(String[] args) {
        // Dane wejściowe do testowania
        List<List<String>> inputsList = Arrays.asList(
                Arrays.asList("Alice", "Bob", "Charlie"),
                Arrays.asList("john", "doe"),
                Collections.singletonList("upper")
        );

        // Oczekiwane wyniki
        List<List<String>> expectedResultsList = Arrays.asList(
                Arrays.asList("ALICE", "BOB", "CHARLIE"),
                Arrays.asList("JOHN", "DOE"),
                Collections.singletonList("UPPER")
        );

        // Konwersja list na tablice List<String>[]
        List<String>[] inputs = inputsList.toArray(new List[0]);
        List<String>[] expectedResults = expectedResultsList.toArray(new List[0]);

        // Użycie TaskExecutor do przeprowadzenia testów na funkcji convertToUpper z klasy Result
        TaskExecutor.executeTest(inputs, expectedResults, Result::convertToUpper);
    }
}
