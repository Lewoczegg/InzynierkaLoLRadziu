import java.io.*;
import java.util.*;

public class Task19Main {
    public static void main(String[] args) {
        // Testowanie metody sumToN za pomocą TaskExecutor
        Integer[] inputs = {5, 10, 1, 0, 7};
        Integer[] expectedResults = {15, 55, 1, 0, 28};

        TaskExecutor.executeTest(inputs, expectedResults, Result::sumToN);
    }
}