import java.io.*;
import java.util.*;

public class Task26Main {
    public static void main(String[] args) {
        // Testowanie klasy Queue za pomocą TaskExecutor
        List<String> operations = Arrays.asList("enqueue 5", "enqueue 10", "peek", "dequeue", "dequeue", "is_empty");
        List<String> expectedResults = Arrays.asList("N/A", "N/A", "5", "5", "10", "true");

        TaskExecutor.executeTestQueue(operations.toArray(new String[0]), expectedResults.toArray(new String[0]), new Queue());
    }
}