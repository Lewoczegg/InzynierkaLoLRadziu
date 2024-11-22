import java.io.*;
import java.util.*;

public class Task25Main {
    public static void main(String[] args) {
        // Testowanie klasy Stack za pomocą TaskExecutor
        List<String> operations = Arrays.asList("push 5", "push 10", "peek", "pop", "pop", "is_empty");
        List<String> expectedResults = Arrays.asList("N/A", "N/A", "10", "10", "5", "true");

        TaskExecutor.executeTestStack(operations.toArray(new String[0]), expectedResults.toArray(new String[0]), new Stack());
    }
}