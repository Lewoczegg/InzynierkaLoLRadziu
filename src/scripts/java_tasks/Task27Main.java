import java.io.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.*;

public class Task27Main {
    public static void main(String[] args) {
        // Testowanie klasy Result za pomocą TaskExecutor
        List<String> operations = Arrays.asList("insert 5", "insert 3", "insert 7", "insert 1", "insert 4", "in_order", "pre_order", "post_order", "find 5", "find 8");
        List<String> expectedResults = Arrays.asList("N/A", "N/A", "N/A", "N/A", "N/A", "[1, 3, 4, 5, 7]", "[5, 3, 1, 4, 7]", "[1, 4, 3, 7, 5]", "true", "false");

        executeTestBinaryTree(operations.toArray(new String[0]), expectedResults.toArray(new String[0]), new BinaryTree());
    }
    public static void executeTestBinaryTree(String[] operations, String[] expectedResults, BinaryTree tree) {
        try {
            // Przygotowanie funkcji do wykonania operacji
            Function<String, String> wrappedFunction = operation -> {
                try {
                    String[] parts = operation.split(" ");
                    String op = parts[0];

                    switch (op) {
                        case "insert":
                            tree.insert(Integer.parseInt(parts[1]));
                            return "N/A";
                        case "find":
                            return String.valueOf(tree.find(Integer.parseInt(parts[1])));
                        case "in_order":
                            return tree.inOrderTraversal().toString();
                        case "pre_order":
                            return tree.preOrderTraversal().toString();
                        case "post_order":
                            return tree.postOrderTraversal().toString();
                        default:
                            return "Unknown operation";
                    }
                } catch (Exception e) {
                    return "Error during operation: " + e.getMessage();
                }
            };

            // Wykonanie testów
            TaskExecutor.executeTestBase(operations, expectedResults, wrappedFunction);

        } catch (Exception e) {
            System.out.println("An error occurred while executing BinaryTree tests: " + e.getMessage());
        }
    }
}
