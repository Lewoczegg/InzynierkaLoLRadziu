public class TaskDaily2Main {
    public static void main(String[] args) {
        String[][] inputs = {
                {"Hello", "World"},
                {"Open", "AI"},
                {"Java", ""},
                {"", "Test"}
        };

        String[] expectedResults = {
                "HelloWorld",
                "OpenAI",
                "Java",
                "Test"
        };

        // Określenie typów dla metody executeTest, aby uniknąć niejednoznaczności
        TaskExecutor.<String[], String>executeTest(inputs, expectedResults, (input) -> Result.concatenateStrings(input[0], input[1]));
    }
}
