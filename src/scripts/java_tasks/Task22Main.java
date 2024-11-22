import java.io.*;
import java.util.*;

public class Task22Main {
    public static void main(String[] args) {
        // Testowanie metody bubbleSort za pomocą TaskExecutor
        List<List<Integer>> inputs = Arrays.asList(
                Arrays.asList(5, 3, 8, 6, 2),
                Arrays.asList(1, 4, 3, 2),
                Arrays.asList(10, 20, 5, 15),
                Arrays.asList(3, 3, 2, 1)
        );
        List<List<Integer>> expectedResults = Arrays.asList(
                Arrays.asList(2, 3, 5, 6, 8),
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 10, 15, 20),
                Arrays.asList(1, 2, 3, 3)
        );

        TaskExecutor.executeTest(inputs.toArray(new List[0]), expectedResults.toArray(new List[0]), Result::bubbleSort);
    }
}