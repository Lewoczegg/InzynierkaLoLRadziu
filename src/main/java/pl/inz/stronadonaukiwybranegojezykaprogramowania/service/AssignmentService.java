package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@Service
public class AssignmentService {
//    private static final String DOCKER_IMAGE_NAME_PREFIX = "my-java-app-";
    public CodeExecutionResponse codeFromGuest(String userCode) {
        CodeExecutionResponse result = new CodeExecutionResponse();
        String uniqueId = UUID.randomUUID().toString();
        String workingDirPath = "/tmp/" + uniqueId; // Unikalny katalog dla każdego wykonania
        Path workingDir = Paths.get(workingDirPath);

        try {
            // 1. Utwórz unikalny katalog roboczy
            Files.createDirectories(workingDir);

            // 2. Zapisz kod użytkownika do Result.java
            Path resultFilePath = workingDir.resolve("Result.java");
            Files.writeString(resultFilePath, userCode);

            // 2. Odczytaj zawartość CombinedResultMain.java
            Path mainFilePath = Paths.get("src/scripts/CombinedResultMain.java");
            Path combinedMainFilePath = workingDir.resolve("CombinedResultMain.java");
            Files.copy(mainFilePath, combinedMainFilePath, StandardCopyOption.REPLACE_EXISTING);

            // 4. Skompiluj kod wewnątrz kontenera Dockera
            String compileCommand = "javac Result.java CombinedResultMain.java";
            String[] compileCmd = {
                    "docker", "run", "--rm",
                    "-v", workingDir.toAbsolutePath() + ":/app",
                    "-w", "/app",
                    "openjdk:17",
                    "sh", "-c", compileCommand
            };

            ProcessBuilder compileProcessBuilder = new ProcessBuilder(compileCmd);
            Process compileProcess = compileProcessBuilder.start();

            // Przechwyć wyjście kompilacji
            String compileOutput = readProcessOutput(compileProcess.getInputStream());
            String compileErrorOutput = readProcessOutput(compileProcess.getErrorStream());

            int compileExitCode = compileProcess.waitFor();

            // Ustawienie wyników kompilacji w obiekcie ExecutionResult
            result.setBuildOutput(compileOutput);
            result.setBuildErrorOutput(compileErrorOutput);

            if (compileExitCode != 0) {
                result.setSuccess(false);
                result.setErrorMessage("Kompilacja nie powiodła się.");
                // Usuń katalog roboczy
                deleteDirectory(workingDir.toFile());
                return result;
            }

            // 5. Uruchom skompilowany kod wewnątrz kontenera Dockera
            String runCommand = "java CombinedResultMain";
            String[] runCmd = {
                    "docker", "run", "--rm",
                    "-v", workingDir.toAbsolutePath() + ":/app",
                    "-w", "/app",
                    "openjdk:17",
                    "sh", "-c", runCommand
            };

            ProcessBuilder runProcessBuilder = new ProcessBuilder(runCmd);
            Process runProcess = runProcessBuilder.start();

            // Przechwyć wyjście wykonania
            String output = readProcessOutput(runProcess.getInputStream());
            String errorOutput = readProcessOutput(runProcess.getErrorStream());

            int runExitCode = runProcess.waitFor();

            result.setOutput(output);
            result.setErrorOutput(errorOutput);

            if (runExitCode != 0) {
                result.setSuccess(false);
                result.setErrorMessage("Wykonanie kodu nie powiodło się.");
                // Usuń katalog roboczy
                deleteDirectory(workingDir.toFile());
                return result;
            }

            result.setSuccess(true);

            // 6. Usuń katalog roboczy
            deleteDirectory(workingDir.toFile());

        } catch (IOException | InterruptedException e) {
            result.setSuccess(false);
            result.setErrorMessage("Wystąpił błąd: " + e.getMessage());
        }

        return result;
    }

    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        deleteDirectory(f);
                    } else {
                        f.delete();
                    }
                }
            }
            directory.delete();
        }
    }

    private String readProcessOutput(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }
}