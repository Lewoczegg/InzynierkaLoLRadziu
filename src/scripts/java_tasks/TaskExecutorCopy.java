import java.io.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.*;

public class TaskExecutor {

    // Generalized method for executing tests with different input-output configurations
    public static <T, R> void executeTest(T[] inputs, R[] expectedResults, Function<T, R> function) {
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

                // Wyświetl wynik testu
                if (result.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + (input instanceof Object[] ? Arrays.deepToString((Object[]) input) : input) + ". Result: " + result);
                } else {
                    System.out.println("Test failed for input: " + (input instanceof Object[] ? Arrays.deepToString((Object[]) input) : input) + ". Expected: " + expectedResult + ", but got: " + result);
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
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }

    // Generalized method for executing tests with two inputs
    public static <T1, T2, R> void executeTest(T1[][] inputs, R[] expectedResults, BiFunction<T1, T2, R> function) {
        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        try {
            for (int i = 0; i < inputs.length; i++) {
                T1[] input = inputs[i];
                R expectedResult = expectedResults[i];

                System.setOut(userOut); // Przekieruj System.out na userOut

                // Wywołaj metodę użytkownika
                R result = function.apply(input[0], (T2) input[1]);

                System.setOut(originalOut); // Przywróć System.out

                // Pobierz wynik z userOutputStream
                String userOutput = userOutputStream.toString().trim();
                userOutputStream.reset();

                // Wyświetl wynik testu
                if (result.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + Arrays.deepToString(input) + ". Result: " + result);
                } else {
                    System.out.println("Test failed for input: " + Arrays.deepToString(input) + ". Expected: " + expectedResult + ", but got: " + result);
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
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }

    public static <T, R> void executeTest(List<T>[] inputs, R[] expectedResults, Function<List<T>, R> function) {
        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        try {
            for (int i = 0; i < inputs.length; i++) {
                List<T> input = inputs[i];
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

                // Wyświetl wynik testu
                if (result.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + input + ". Result: " + result);
                } else {
                    System.out.println("Test failed for input: " + input + ". Expected: " + expectedResult + ", but got: " + result);
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
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }

    public static <T> void executeTest(T[] inputs, String[] expectedResults, Consumer<T> function) {
        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        try {
            for (int i = 0; i < inputs.length; i++) {
                T input = inputs[i];
                String expectedResult = expectedResults[i];

                // Przekieruj System.out na userOut, aby przechwycić wyjście użytkownika
                System.setOut(userOut);

                // Wywołaj metodę użytkownika
                function.accept(input);

                // Przywróć oryginalny System.out
                System.setOut(originalOut);

                // Pobierz wynik z userOutputStream
                String userOutput = userOutputStream.toString().trim();
                userOutputStream.reset();

                // Wyświetl wynik testu
                if (userOutput.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + input + ". Result: " + userOutput);
                } else {
                    System.out.println("Test failed for input: " + input + ". Expected: " + expectedResult + ", but got: " + userOutput);
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
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }
    public static void executeTest(String[] operations, String[] expectedResults, BinaryTree tree) {
        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        try {
            for (int i = 0; i < operations.length; i++) {
                String operation = operations[i];
                String[] parts = operation.split(" ");
                String op = parts[0];
                String expectedResult = expectedResults[i];

                // Przekieruj System.out na userOut, aby przechwycić wyjście użytkownika
                System.setOut(userOut);

                // Wywołaj metodę użytkownika
                String userOutput;
                switch (op) {
                    case "insert":
                        int value = Integer.parseInt(parts[1]);
                        tree.insert(value);
                        userOutput = "N/A";
                        break;
                    case "find":
                        boolean found = tree.find(Integer.parseInt(parts[1]));
                        userOutput = String.valueOf(found);
                        break;
                    case "in_order":
                        userOutput = tree.inOrderTraversal().toString();
                        break;
                    case "pre_order":
                        userOutput = tree.preOrderTraversal().toString();
                        break;
                    case "post_order":
                        userOutput = tree.postOrderTraversal().toString();
                        break;
                    default:
                        userOutput = "Unknown operation";
                        break;
                }

                // Przywróć oryginalny System.out
                System.setOut(originalOut);

                // Pobierz wynik z userOutputStream
                String output = userOutputStream.toString().trim();
                userOutputStream.reset();

                // Wyświetl wynik testu
                if (userOutput.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + operation + ". Result: " + userOutput);
                } else {
                    System.out.println("Test failed for input: " + operation + ". Expected: " + expectedResult + ", but got: " + userOutput);
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
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }
    public static void executeTest(String[] operations, String[] expectedResults, Stack stack) {
        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        try {
            for (int i = 0; i < operations.length; i++) {
                String operation = operations[i];
                String[] parts = operation.split(" ");
                String op = parts[0];
                String expectedResult = expectedResults[i];

                // Przekieruj System.out na userOut, aby przechwycić wyjście użytkownika
                System.setOut(userOut);

                // Wywołaj metodę użytkownika
                String userOutput;
                switch (op) {
                    case "push":
                        int value = Integer.parseInt(parts[1]);
                        stack.push(value);
                        userOutput = "N/A";
                        break;
                    case "pop":
                        int poppedValue = (int) stack.pop();
                        userOutput = String.valueOf(poppedValue);
                        break;
                    case "peek":
                        int peekedValue = (int) stack.peek();
                        userOutput = String.valueOf(peekedValue);
                        break;
                    case "is_empty":
                        boolean isEmpty = stack.isEmpty();
                        userOutput = String.valueOf(isEmpty);
                        break;
                    default:
                        userOutput = "Unknown operation";
                        break;
                }

                // Przywróć oryginalny System.out
                System.setOut(originalOut);

                // Pobierz wynik z userOutputStream
                String output = userOutputStream.toString().trim();
                userOutputStream.reset();

                // Wyświetl wynik testu
                if (userOutput.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + operation + ". Result: " + userOutput);
                } else {
                    System.out.println("Test failed for input: " + operation + ". Expected: " + expectedResult + ", but got: " + userOutput);
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
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }

    public static void executeTest(String[] operations, String[] expectedResults, Queue queue) {
        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        try {
            for (int i = 0; i < operations.length; i++) {
                String operation = operations[i];
                String[] parts = operation.split(" ");
                String op = parts[0];
                String expectedResult = expectedResults[i];

                // Przekieruj System.out na userOut, aby przechwycić wyjście użytkownika
                System.setOut(userOut);

                // Wywołaj metodę użytkownika
                String userOutput;
                switch (op) {
                    case "enqueue":
                        int value = Integer.parseInt(parts[1]);
                        queue.enqueue(value);
                        userOutput = "N/A";
                        break;
                    case "dequeue":
                        // Rzutowanie wartości wynikowej z obiektu na int
                        int dequeuedValue = (int) queue.dequeue();
                        userOutput = String.valueOf(dequeuedValue);
                        break;
                    case "peek":
                        // Rzutowanie wartości wynikowej z obiektu na int
                        int peekedValue = (int) queue.peek();
                        userOutput = String.valueOf(peekedValue);
                        break;
                    case "is_empty":
                        boolean isEmpty = queue.isEmpty();
                        userOutput = String.valueOf(isEmpty);
                        break;
                    default:
                        userOutput = "Unknown operation";
                        break;
                }

                // Przywróć oryginalny System.out
                System.setOut(originalOut);

                // Pobierz wynik z userOutputStream
                String output = userOutputStream.toString().trim();
                userOutputStream.reset();

                // Wyświetl wynik testu
                if (userOutput.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + operation + ". Result: " + userOutput);
                } else {
                    System.out.println("Test failed for input: " + operation + ". Expected: " + expectedResult + ", but got: " + userOutput);
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
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }
    public static void executeTest(String[] operations, String[] expectedResults, BinaryTree tree) {
        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        try {
            for (int i = 0; i < operations.length; i++) {
                String operation = operations[i];
                String[] parts = operation.split(" ");
                String op = parts[0];
                String expectedResult = expectedResults[i];

                // Przekieruj System.out na userOut, aby przechwycić wyjście użytkownika
                System.setOut(userOut);

                // Wywołaj metodę użytkownika
                String userOutput;
                switch (op) {
                    case "insert":
                        int value = Integer.parseInt(parts[1]);
                        tree.insert(value);
                        userOutput = "N/A";
                        break;
                    case "find":
                        boolean found = tree.find(Integer.parseInt(parts[1]));
                        userOutput = String.valueOf(found);
                        break;
                    case "in_order_traversal":
                        userOutput = tree.inOrderTraversal().toString();
                        break;
                    case "pre_order_traversal":
                        userOutput = tree.preOrderTraversal().toString();
                        break;
                    case "post_order_traversal":
                        userOutput = tree.postOrderTraversal().toString();
                        break;
                    default:
                        userOutput = "Unknown operation";
                        break;
                }

                // Przywróć oryginalny System.out
                System.setOut(originalOut);

                // Pobierz wynik z userOutputStream
                String output = userOutputStream.toString().trim();
                userOutputStream.reset();

                // Wyświetl wynik testu
                if (userOutput.equals(expectedResult)) {
                    System.out.println("Test passed for input: " + operation + ". Result: " + userOutput);
                } else {
                    System.out.println("Test failed for input: " + operation + ". Expected: " + expectedResult + ", but got: " + userOutput);
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
        try (FileOutputStream fos = new FileOutputStream("user_output.txt")) {
            fos.write(userOutputStream.toByteArray());
        } catch (IOException e) {
            System.err.println("Error writing user output: " + e.getMessage());
        }
    }

}

