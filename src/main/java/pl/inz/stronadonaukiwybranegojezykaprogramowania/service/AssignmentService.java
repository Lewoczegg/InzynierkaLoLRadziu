package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@Service
public class AssignmentService {
    public CodeExecutionResponse codeFromGuest(String userCode, String taskId) {
        CodeExecutionResponse result = new CodeExecutionResponse();
        String uniqueId = UUID.randomUUID().toString();
        String workingDirPath = "/tmp/" + uniqueId;
        Path workingDir = Paths.get(workingDirPath);

        try {
            Files.createDirectories(workingDir);

            Path resultFilePath = workingDir.resolve("Result.java");
            Files.writeString(resultFilePath, userCode);

            String mainFileName = "Task" + taskId + "Main.java";
            Path mainFilePath = Paths.get("src/scripts/"  + mainFileName);

            if (!Files.exists(mainFilePath)) {
                result.setSuccess(false);
                result.setErrorMessage("Nie znaleziono zadania o podanym ID.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            Path combinedMainFilePath = workingDir.resolve(mainFileName);
            Files.copy(mainFilePath, combinedMainFilePath, StandardCopyOption.REPLACE_EXISTING);

            String compileCommand = "javac Result.java " + mainFileName;
            String[] compileCmd = {
                    "docker", "run", "--rm",
                    "-v", workingDir.toAbsolutePath() + ":/app",
                    "-w", "/app",
                    "openjdk:17",
                    "sh", "-c", compileCommand
            };

            ProcessBuilder compileProcessBuilder = new ProcessBuilder(compileCmd);
            Process compileProcess = compileProcessBuilder.start();

            String compileOutput = readProcessOutput(compileProcess.getInputStream());
            String compileErrorOutput = readProcessOutput(compileProcess.getErrorStream());

            int compileExitCode = compileProcess.waitFor();

            result.setBuildOutput(compileOutput);
            result.setBuildErrorOutput(compileErrorOutput);

            if (compileExitCode != 0) {
                result.setSuccess(false);
                result.setErrorMessage("Kompilacja nie powiodła się.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            String mainClassName = mainFileName.replace(".java", "");
            String runCommand = "java " + mainClassName;
            String[] runCmd = {
                    "docker", "run", "--rm",
                    "-v", workingDir.toAbsolutePath() + ":/app",
                    "-w", "/app",
                    "openjdk:17",
                    "sh", "-c", runCommand
            };

            ProcessBuilder runProcessBuilder = new ProcessBuilder(runCmd);
            Process runProcess = runProcessBuilder.start();

            String output = readProcessOutput(runProcess.getInputStream());
            String errorOutput = readProcessOutput(runProcess.getErrorStream());

            int runExitCode = runProcess.waitFor();

            result.setOutput(output);
            result.setErrorOutput(errorOutput);

            if (runExitCode != 0) {
                result.setSuccess(false);
                result.setErrorMessage("Wykonanie kodu nie powiodło się.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            result.setSuccess(true);

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