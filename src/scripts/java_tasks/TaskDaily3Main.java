import java.io.*;
import java.util.*;

public class TaskDaily3Main {
    public static void main(String[] args) {
        String[] inputs = {
                "Hello world, welcome to the world of Java.",
                "Java is great.",
                "The world is big."
        };

        Integer[] expectedResults = {2, 0, 1};

        String phrase = "world";

        TaskExecutor.<String, Integer>executeTest(inputs, expectedResults, input -> Result.countPhraseOccurrences(input, phrase));
    }
}
