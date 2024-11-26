import java.util.List;
import java.util.Arrays;

public class Task24Main {
    public static void main(String[] args) {
        List<Integer>[] inputs = new List[]{
                Arrays.asList(64, 25, 12, 22, 11),
                Arrays.asList(5, 3, 8, 4, 2),
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(10, 9, 8, 7, 6),
                Arrays.asList(20, 15, 30, 5, 25),
                Arrays.asList(100, 50, 25, 75, 10)
        };

        List<Integer>[] expectedResults = new List[]{
                Arrays.asList(11, 12, 22, 25, 64),
                Arrays.asList(2, 3, 4, 5, 8),
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(6, 7, 8, 9, 10),
                Arrays.asList(5, 15, 20, 25, 30),
                Arrays.asList(10, 25, 50, 75, 100)
        };

        TaskExecutor.executeTest(inputs, expectedResults, Result::selectionSort);
    }
}