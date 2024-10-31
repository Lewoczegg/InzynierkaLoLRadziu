package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Assignment;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.AssignmentRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.LessonRepository;

import java.io.*;
import java.nio.file.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private LessonRepository lessonRepository;
    public CodeExecutionResponse codeFromGuest(String userCode, String taskId, String language){
        if ("java".equalsIgnoreCase(language)) {
            return executeJavaCode(userCode, taskId);
        } else if ("python".equalsIgnoreCase(language)) {
            return executePythonCode(userCode, taskId);
        } else {
            CodeExecutionResponse result = new CodeExecutionResponse();
            result.setSuccess(false);
            result.setErrorMessage("Unsupported language: " + language);
            return result;
        }
    }
    public CodeExecutionResponse executePythonCode(String userCode, String taskId) {
        CodeExecutionResponse result = new CodeExecutionResponse();
        String uniqueId = UUID.randomUUID().toString();
        String workingDirPath = "/path/to/temp/" + uniqueId;
        Path workingDir = Paths.get(workingDirPath);

        try {
            Files.createDirectories(workingDir);

            Path userCodePath = workingDir.resolve("user_code.py");
            Files.writeString(userCodePath, userCode);

            String testFileName = "task" + taskId + ".py";
            Path testFilePath = Paths.get("src/scripts/python_tasks/" + testFileName);

            if (!Files.exists(testFilePath)) {
                result.setSuccess(false);
                result.setErrorMessage("Nie znaleziono zadania o podanym ID.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            Path copiedTestFilePath = workingDir.resolve(testFileName);
            Files.copy(testFilePath, copiedTestFilePath, StandardCopyOption.REPLACE_EXISTING);

            String runCommand = "python " + testFileName;
            String[] runCmd = {
                    "docker", "run", "--rm",
                    "-v", workingDir.toAbsolutePath() + ":/app",
                    "-w", "/app",
                    "python:3.9",
                    "sh", "-c", runCommand
            };

            ProcessBuilder runProcessBuilder = new ProcessBuilder(runCmd);
            Process runProcess = runProcessBuilder.start();

            String output = readProcessOutput(runProcess.getInputStream());
            String errorOutput = readProcessOutput(runProcess.getErrorStream());

            int runExitCode = runProcess.waitFor();

            result.setOutput(output);

            if (runExitCode != 0) {
                result.setSuccess(false);
                result.setOutput(errorOutput);
                result.setErrorMessage("Wykonanie kodu nie powiodło się.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            Path userOutputPath = workingDir.resolve("user_output.txt");
            if (Files.exists(userOutputPath)) {
                String userOutput = Files.readString(userOutputPath);
                result.setUserOutput(userOutput);
            } else {
                result.setUserOutput("");
            }

            result.setSuccess(true);

            deleteDirectory(workingDir.toFile());

        } catch (IOException | InterruptedException e) {
            result.setSuccess(false);
            result.setErrorMessage("Wystąpił błąd: " + e.getMessage());
        }

        return result;
    }

    public CodeExecutionResponse executeJavaCode(String userCode, String taskId){
        CodeExecutionResponse result = new CodeExecutionResponse();
        String uniqueId = UUID.randomUUID().toString();
        String workingDirPath = "/tmp/" + uniqueId;
        Path workingDir = Paths.get(workingDirPath);

        try {
            Files.createDirectories(workingDir);

            Path resultFilePath = workingDir.resolve("Result.java");
            Files.writeString(resultFilePath, userCode);

            String mainFileName = "Task" + taskId + "Main.java";
            Path mainFilePath = Paths.get("src/scripts/java_tasks/"  + mainFileName);

            if (!Files.exists(mainFilePath)) {
                result.setSuccess(false);
                result.setErrorMessage("Nie znaleziono zadania o podanym ID.");
                // Usuń katalog roboczy
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

            if (compileExitCode != 0) {
                result.setOutput(compileErrorOutput);
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

            if (runExitCode != 0) {
                result.setSuccess(false);
                result.setErrorMessage("Wykonanie kodu nie powiodło się.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            Path userOutputPath = workingDir.resolve("user_output.txt");
            if (Files.exists(userOutputPath)) {
                String userOutput = Files.readString(userOutputPath);
                result.setUserOutput(userOutput);
            } else {
                result.setUserOutput("");
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

    public Assignment createAssignment(String title, String description, Long lessonId) {
        Optional<Lesson> lessonOpt = lessonRepository.findById(lessonId);
        if (lessonOpt.isEmpty()){
            throw new RuntimeException("Lesson not found");
        }
        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setLesson(lessonOpt.get());
        assignment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }
}