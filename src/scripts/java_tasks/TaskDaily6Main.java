import java.io.*;
import java.util.*;

public class TaskDaily6Main {
    public static void main(String[] args) {
        // Dane wejściowe do testowania - lista map
        List<Map<String, String>> inputsList = Arrays.asList(
                new HashMap<>(Map.of("Apple", "A fruit", "Dog", "An animal")),
                new HashMap<>(Map.of("Book", "Something to read")),
                new HashMap<>(Map.of())
        );

        // Nowe wpisy, które mają być dodane do każdej mapy
        List<String[]> newEntriesList = Arrays.asList(
                new String[]{"Car", "A vehicle"},
                new String[]{"Pen", "A tool for writing"},
                new String[]{"Key", "Used for unlocking"}
        );

        // Oczekiwane wyniki po dodaniu nowych wpisów
        List<Map<String, String>> expectedResultsList = Arrays.asList(
                new HashMap<>(Map.of("Apple", "A fruit", "Dog", "An animal", "Car", "A vehicle")),
                new HashMap<>(Map.of("Book", "Something to read", "Pen", "A tool for writing")),
                new HashMap<>(Map.of("Key", "Used for unlocking"))
        );

        // Konwersja list na tablice
        Map<String, String>[] inputs = inputsList.toArray(new Map[0]);
        String[][] newEntries = newEntriesList.toArray(new String[0][]);
        Map<String, String>[] expectedResults = expectedResultsList.toArray(new Map[0]);

        // Użycie TaskExecutor do przeprowadzenia testów na funkcji addEntry z klasy Result
        TaskExecutor.<Map<String, String>, String[], Map<String, String>>executeTest(inputs, newEntries, expectedResults, (input, entry) -> Result.addEntry(input, entry));
    }
}
