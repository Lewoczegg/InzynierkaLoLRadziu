import java.io.*;
import java.util.*;

public class TaskDaily1Main {
    public static void main(String[] args) {
        String[] inputs = {
                "Hello, World!",
                "",
                "OpenAI"
        };

        // Zamiana int[] na Integer[], aby była zgodna z generics
        Integer[] expectedResults = {
                13,
                0,
                6
        };

        // Określenie typów dla metody executeTest, aby uniknąć niejednoznaczności
        TaskExecutor.<String, Integer>executeTest(inputs, expectedResults, Result::calculateStringLength);
    }
}
