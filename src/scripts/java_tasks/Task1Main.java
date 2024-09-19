import java.io.*;

public class Task1Main {
    public static void main(String[] args) {
        int[][] inputs = {
                {1, 2},
                {3, 4},
                {10, 20}
        };

        int[] expectedResults = {
                3,
                7,
                30
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
            Result.sum(inputs[0][0], inputs[0][1]);

            // Przywróć oryginalny System.out
            System.setOut(originalOut);

            // Wykonaj testy, przekierowując System.out na nullOut, aby zignorować wyjście użytkownika
            for (int i = 0; i < inputs.length; i++) {
                int[] input = inputs[i];
                int expectedResult = expectedResults[i];

                System.setOut(nullOut); // Przekieruj System.out na nullOut

                // Wywołaj metodę użytkownika
                int result = Result.sum(input[0], input[1]);

                System.setOut(originalOut); // Przywróć System.out

                // Wyświetl wynik testu
                if (result == expectedResult) {
                    System.out.println("Test passed for input: " + input[0] + " and " + input[1] + ". Result: " + result);
                } else {
                    System.out.println("Test failed for input: " + input[0] + " and " + input[1] + ". Expected: " + expectedResult + ", but got: " + result);
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
