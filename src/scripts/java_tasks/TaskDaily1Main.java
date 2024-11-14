import java.io.*;

public class TaskDaily1Main {
    public static void main(String[] args) {
        String[] inputs = {
                "Hello, World!",
                "",
                "OpenAI"
        };

        int[] expectedResults = {
                13,
                0,
                6
        };

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
            // Przekieruj System.out na userOut, aby przechwycić wyjście użytkownika
            System.setOut(userOut);

            // Wywołaj metodę użytkownika raz, aby przechwycić jego wyjście

            Result.textLength(inputs[0]); // Zakładamy, że istnieje metoda użytkownika Result.textLength(String text)


            // Przywróć oryginalny System.out
            System.setOut(originalOut);

            // Wykonaj testy, przekierowując System.out na nullOut, aby zignorować wyjście użytkownika
            for (int i = 0; i < inputs.length; i++) {
                String input = inputs[i];
                int expectedResult = expectedResults[i];

                System.setOut(nullOut); // Przekieruj System.out na nullOut

                // Wywołaj metodę użytkownika
                int result = Result.textLength(input);

                System.setOut(originalOut); // Przywróć System.out

                // Wyświetl wynik testu
                if (result == expectedResult) {
                    System.out.println("Test passed for input: \"" + input + "\". Result: " + result);
                } else {
                    System.out.println("Test failed for input: \"" + input + "\". Expected: " + expectedResult + ", but got: " + result);
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