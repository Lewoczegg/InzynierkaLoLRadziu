import java.io.*;

public class Task2Main {
    public static void main(String[] args) {
        String[] testInputs = {"hello", "world", "OpenAI", "Java"};
        String[] expectedOutputs = {"olleh", "dlrow", "IAnepO", "avaJ"};

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
            Result.reverseString(testInputs[0]);
            System.setOut(originalOut);

            // Wykonaj testy
            for (int i = 0; i < testInputs.length; i++) {
                System.setOut(nullOut); // Ignoruj wyjście użytkownika
                String result = Result.reverseString(testInputs[i]);
                System.setOut(originalOut); // Przywróć System.out

                if (result.equals(expectedOutputs[i])) {
                    System.out.println("Test passed for input: \"" + testInputs[i] + "\""+ ". Result: " + result);
                } else {
                    System.out.println("Test failed for input: \"" + testInputs[i] + "\". Expected: \"" + expectedOutputs[i] + "\", but got: \"" + result + "\"");
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

