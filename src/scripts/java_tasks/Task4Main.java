import java.io.*;

public class Task4Main {
    public static void main(String[] args) {
        int[] testInputs = {0, 1, 3, 5, 7};
        int[] expectedOutputs = {1, 1, 6, 120, 5040};

        boolean allTestsPassed = true;

        // Zachowaj oryginalny System.out
        PrintStream originalOut = System.out;

        // Strumień do przechwytywania wyjścia użytkownika
        ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
        PrintStream userOut = new PrintStream(userOutputStream);

        // Strumień ignorujący dane wyjściowe
        PrintStream nullOut = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                // Ignoruje dane
            }
        });

        try {
            // Przechwyć wyjście użytkownika
            System.setOut(userOut);
            Result.factorial(testInputs[0]);
            System.setOut(originalOut);

            // Wykonaj testy
            for (int i = 0; i < testInputs.length; i++) {
                System.setOut(nullOut); // Ignoruj wyjście użytkownika
                int result = Result.factorial(testInputs[i]);
                System.setOut(originalOut); // Przywróć System.out

                if (result == expectedOutputs[i]) {
                    System.out.println("Test passed for input: " + testInputs[i]);
                } else {
                    System.out.println("Test failed for input: " + testInputs[i] + ". Expected: " + expectedOutputs[i] + ", but got: " + result);
                    allTestsPassed = false;
                }
            }

            if (allTestsPassed) {
                System.out.println("All tests passed!");
            } else {
                System.out.println("Some tests failed.");
            }

        } catch (Exception e) {
            System.setOut(originalOut);
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
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
