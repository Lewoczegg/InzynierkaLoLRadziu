import java.io.*;

public class Task3Main {
    public static void main(String[] args) {
        int[] testInputs = {2, 4, 5, 9, 13, 17, 20, 23, 29, 31};
        boolean[] expectedOutputs = {true, false, true, false, true, true, false, true, true, true};

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
            Result.isPrime(testInputs[0]);

            // Przywróć oryginalny System.out
            System.setOut(originalOut);

            // Wykonaj testy, przekierowując System.out na nullOut, aby zignorować wyjście użytkownika
            for (int i = 0; i < testInputs.length; i++) {
                System.setOut(nullOut); // Przekieruj System.out na nullOut

                // Wywołaj metodę użytkownika
                boolean result = Result.isPrime(testInputs[i]);

                System.setOut(originalOut); // Przywróć System.out

                // Wyświetl wynik testu
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
            // Przywróć System.out w przypadku wyjątku
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


