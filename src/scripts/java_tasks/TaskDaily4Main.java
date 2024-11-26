import java.io.*;
import java.util.*;

public class TaskDaily4Main {
    public static void main(String[] args) {
        List<List<String>> inputsList = Arrays.asList(
                Arrays.asList("Alice", "Bob", "Charlie"),
                Arrays.asList("john", "doe"),
                Collections.singletonList("upper")
        );

        List<List<String>> expectedResultsList = Arrays.asList(
                Arrays.asList("ALICE", "BOB", "CHARLIE"),
                Arrays.asList("JOHN", "DOE"),
                Collections.singletonList("UPPER")
        );

        List<String>[] inputs = inputsList.toArray(new List[0]);
        List<String>[] expectedResults = expectedResultsList.toArray(new List[0]);

        TaskExecutor.executeTest(inputs, expectedResults, Result::convertToUpper);
    }
}
