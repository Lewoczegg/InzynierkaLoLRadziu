public class Task2Main {
    public static void main(String[] args) {
        Integer[][] inputs = {
                {3, 5},
                {-2, 7},
                {0, 0},
                {10, -10},
                {100, 200}
        };

        Integer[] expectedResults = {
                8,
                5,
                0,
                0,
                300
        };

        TaskExecutor.executeTest(inputs, expectedResults, Result::addTwoIntegers);
    }
}