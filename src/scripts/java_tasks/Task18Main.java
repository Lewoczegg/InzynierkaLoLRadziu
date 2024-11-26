import java.io.*;
import java.util.*;

public class Task18Main {
    public static void main(String[] args) {
        Integer[] inputs = {5, 0, 3, 7, 10};
        Integer[] expectedResults = {120, 1, 6, 5040, 3628800};

        TaskExecutor.executeTest(inputs, expectedResults, Result::factorial);
    }
}