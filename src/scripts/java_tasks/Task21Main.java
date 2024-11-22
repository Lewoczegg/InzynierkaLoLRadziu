import java.io.*;
import java.util.*;
import java.util.function.Consumer;

public class Task21Main {
    public static void main(String[] args) {
        // Testowanie metody towerOfHanoi za pomocą TaskExecutor
        Integer[] inputs = {2, 3};
        String[] expectedResults = {
                "Move disk from A to BMove disk from A to CMove disk from B to C",
                "Move disk from A to CMove disk from A to BMove disk from C to BMove disk from A to CMove disk from B to AMove disk from B to CMove disk from A to C"
        };

        TaskExecutor.executeTest(inputs, expectedResults, (n) -> {
            StringBuilder output = new StringBuilder();
            Consumer<String> logger = output::append;
            Result.towerOfHanoi(n, 'A', 'C', 'B', logger);
            System.out.println(output.toString().trim());
        });
    }
}
