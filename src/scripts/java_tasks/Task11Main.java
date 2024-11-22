import java.util.List;
import java.util.Arrays;

public class Task11Main {
    public static void main(String[] args) {
        // Testowanie metody calculateSum za pomocą TaskExecutor
        List<Integer>[] inputs = new List[]{
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(10, 20, 30, 40, 50),
                Arrays.asList(-1, -2, -3, -4, -5),
                Arrays.asList(0, 0, 0, 0, 0)
        };
        Integer[] expectedResults = {15, 150, -15, 0};

        TaskExecutor.executeTest(inputs, expectedResults, Result::calculateSum);
    }
}
