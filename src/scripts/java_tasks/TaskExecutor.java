import java.io.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.*;

public class TaskExecutor {

    // Podstawowa metoda do uruchamiania testów
    public static <T, R> void executeTestBase(T[] inputs, R[] expectedResults, Function<T, R> function) {
        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        try {
            for (int i = 0; i < inputs.length; i++) {
                T input = inputs[i];
                R expectedResult = expectedResults[i];

                // Przekieruj System.out na userOut, aby przechwycić wyjście użytkownika
                System.setOut(userOut);

                // Wywołaj metodę użytkownika
                R result = function.apply(input);

                // Przywróć oryginalny System.out
                System.setOut(originalOut);

                // Pobierz wynik z userOutputStream
                String userOutput = userOutputStream.toString().trim();
                userOutputStream.reset();

                // Konwersja tablic na bardziej czytelny format
                String inputStr = (input instanceof int[] ? Arrays.toString((int[]) input) :
                        (input instanceof Object[] ? Arrays.deepToString((Object[]) input) : String.valueOf(input)));

                String expectedResultStr = (expectedResult instanceof Object[] ? Arrays.toString((Object[]) expectedResult) : String.valueOf(expectedResult));
                String resultStr = (result instanceof Object[] ? Arrays.toString((Object[]) result) : String.valueOf(result));

                // Wyświetl wynik testu
                if (result.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + inputStr + ". Result: " + resultStr);
                } else {
                    System.out.println("Test failed for input: " + inputStr + ". Expected: " + expectedResultStr + ", but got: " + resultStr);
                    allTestsPassed = false;
                }
            }

            if (allTestsPassed) {
                System.out.println("All tests passed!");
            } else {
                System.out.println("Some tests failed.");
            }

        } catch (Exception e) {
            // Przywróć oryginalny System.out w przypadku wyjątku
            System.setOut(originalOut);
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Upewnij się, że System.out jest przywrócony
            System.setOut(originalOut);
        }

        // Zapisz wyjście użytkownika do pliku
        writeOutputToFile(userOutputStream);
    }

    // Ogólna metoda do zapisu wyjścia do pliku
    private static void writeOutputToFile(ByteArrayOutputStream userOutputStream) {
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }
    // Metoda do wykonywania testów dla funkcji dwóch argumentów
    public static <T1, T2, R> void executeTest(T1[] inputs, T2[] secondInputs, R[] expectedResults, BiFunction<T1, T2, R> function) {
        Function<Integer, R> wrappedFunction = (index) -> function.apply(inputs[index], secondInputs[index]);
        Integer[] indices = new Integer[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            indices[i] = i;
        }
        executeTestBase(indices, expectedResults, wrappedFunction);
    }

    // Metoda do wykonywania testów dla funkcji jednego argumentu
    public static <T, R> void executeTest(T[] inputs, R[] expectedResults, Function<T, R> function) {
        executeTestBase(inputs, expectedResults, function);
    }

    // Metoda do wykonywania testów dla funkcji dwóch argumentów
    public static <T1, T2, R> void executeTest(T1[][] inputs, R[] expectedResults, BiFunction<T1, T2, R> function) {
        Function<T1[], R> wrappedFunction = input -> function.apply(input[0], (T2) input[1]);
        executeTestBase(inputs, expectedResults, wrappedFunction);
    }

    // Metoda do wykonywania testów dla list
    public static <T, R> void executeTest(List<T>[] inputs, R[] expectedResults, Function<List<T>, R> function) {
        executeTestBase(inputs, expectedResults, function);
    }

    // Metoda do wykonywania testów dla funkcji konsumującej jeden argument
    public static <T> void executeTest(T[] inputs, String[] expectedResults, Consumer<T> function) {
        Function<T, String> wrappedFunction = input -> {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            PrintStream newOut = new PrintStream(outputStream);

            try {
                System.setOut(newOut);
                function.accept(input);
            } finally {
                System.setOut(originalOut);
            }

            return outputStream.toString().trim();
        };

        executeTestBase(inputs, expectedResults, wrappedFunction);
    }

    // Metoda do wykonywania testów dla Stack
    public static void executeTestStack(String[] operations, String[] expectedResults, Stack stack) {
        Function<String, String> wrappedFunction = operation -> {
            String[] parts = operation.split(" ");
            String op = parts[0];

            switch (op) {
                case "push":
                    stack.push(Integer.parseInt(parts[1]));
                    return "N/A";
                case "pop":
                    return String.valueOf(stack.pop());
                case "peek":
                    return String.valueOf(stack.peek());
                case "is_empty":
                    return String.valueOf(stack.isEmpty());
                default:
                    return "Unknown operation";
            }
        };

        executeTestBase(operations, expectedResults, wrappedFunction);
    }

    // Metoda do wykonywania testów dla Queue
    public static void executeTestQueue(String[] operations, String[] expectedResults, Queue queue) {
        Function<String, String> wrappedFunction = operation -> {
            String[] parts = operation.split(" ");
            String op = parts[0];

            switch (op) {
                case "enqueue":
                    queue.add(Integer.parseInt(parts[1]));
                    return "N/A";
                case "dequeue":
                    return String.valueOf(queue.remove());
                case "peek":
                    return String.valueOf(queue.peek());
                case "is_empty":
                    return String.valueOf(queue.isEmpty());
                default:
                    return "Unknown operation";
            }
        };

        executeTestBase(operations, expectedResults, wrappedFunction);
    }
}
