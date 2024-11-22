import java.io.*;
import java.util.*;

public class TaskDaily3Main {
    public static void main(String[] args) {
        // Dane wejściowe do testowania
        String[] inputs = {
                "Hello world, welcome to the world of Java.",
                "Java is great.",
                "The world is big."
        };

        // Zamiana int[] na Integer[], aby była zgodna z generics
        Integer[] expectedResults = {2, 0, 1};

        // Fraza do wyszukania
        String phrase = "world";

        // Użycie TaskExecutor do przeprowadzenia testów na funkcji countPhraseOccurrences z klasy Result
        TaskExecutor.<String, Integer>executeTest(inputs, expectedResults, input -> Result.countPhraseOccurrences(input, phrase));
    }
}
