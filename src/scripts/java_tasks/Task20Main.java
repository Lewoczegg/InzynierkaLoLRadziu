import java.io.*;
import java.util.*;

public class Task20Main {
    public static void main(String[] args) {
        // Testowanie metody fibonacci za pomocą TaskExecutor
        Integer[] inputs = {0, 1, 5, 7, 10};
        Integer[] expectedResults = {0, 1, 5, 13, 55};

        TaskExecutor.executeTest(inputs, expectedResults, Result::fibonacci);
    }
}