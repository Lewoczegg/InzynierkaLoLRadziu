import java.util.*;

public class Task10Main {
    public static void main(String[] args) {
        Integer[][] inputs = {{1}, {2}};
        List<Integer>[] expectedResults = new List[]{
                Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100),
                Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
        };

        TaskExecutor.executeTest(inputs, expectedResults, input -> Result.defineArray());
    }
}