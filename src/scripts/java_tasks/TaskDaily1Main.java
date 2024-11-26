import java.io.*;
import java.util.*;

public class TaskDaily1Main {
    public static void main(String[] args) {
        String[] inputs = {
                "Hello, World!",
                "",
                "OpenAI"
        };

        Integer[] expectedResults = {
                13,
                0,
                6
        };

        TaskExecutor.<String, Integer>executeTest(inputs, expectedResults, Result::calculateStringLength);
    }
}
