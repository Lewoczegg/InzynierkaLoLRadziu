package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.AssignmentResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.CodeExecutionResponse;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.dto.AssignmentDTO;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.*;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.*;

import java.io.*;
import java.nio.file.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final LessonRepository lessonRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ProgressService progressService;
    public AssignmentService(AssignmentRepository assignmentRepository, LessonRepository lessonRepository, SubmissionRepository submissionRepository, UserRepository userRepository, CourseRepository courseRepository, ProgressService progressService) {
        this.assignmentRepository = assignmentRepository;
        this.lessonRepository = lessonRepository;
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.progressService = progressService;
    }

    public CodeExecutionResponse submitCode(String userCode, String taskId, String language) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        String userTitle = String.valueOf(user.getTitle());

        Assignment assignment = assignmentRepository.findByAssignmentId(Long.valueOf(taskId));
        if (assignment == null) {
            throw new IllegalArgumentException("Assignment not found");
        }
        String assignmentTitle = String.valueOf(assignment.getTitleLvl());

        if (userTitle.equals("BEGINNER") && !assignmentTitle.equals("BEGINNER")) {
            throw new IllegalStateException("User with title BEGINNER can only submit assignments with title BEGINNER");
        } else if (userTitle.equals("INTERMEDIATE") && !assignmentTitle.equals("BEGINNER") && !assignmentTitle.equals("INTERMEDIATE")) {
            throw new IllegalStateException("User with title INTERMEDIATE can only submit assignments with title BEGINNER or INTERMEDIATE");
        }

        CodeExecutionResponse grade = codeFromGuest(userCode, taskId, language);

        Pattern pattern = Pattern.compile("All tests passed!");
        Matcher matcher = pattern.matcher(grade.getOutput());

        if (matcher.find()) {

            Submission submission = new Submission();
            submission.setAssignment(assignmentRepository.findByAssignmentId(Long.valueOf(taskId)));
            submission.setUser(userRepository.findByUsername(username));
            submission.setContent(userCode);
            submission.setGrade(100F);
            submission.setSubmittedAt(new Timestamp(System.currentTimeMillis()));
            submission.setGradedAt(new Timestamp(System.currentTimeMillis()));

            submissionRepository.save(submission);
            progressService.markLessonAsCompleted(lessonRepository.findById(assignmentRepository.findById(Long.valueOf(taskId)).get().getLesson().getLessonId()).get().getLessonId());
        } else {
            grade.setSuccess(false);
            grade.setUserOutput("");
            grade.setBuildOutput("");
            grade.setOutput("Code submission did not pass all tests");
        }
        return grade;
    }



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

            // Zapisz kod użytkownika w pliku user_code.py
            Path userCodePath = workingDir.resolve("user_code.py");
            Files.writeString(userCodePath, userCode);

            // Sprawdź, czy plik testowy istnieje
            String testFileName = "task" + taskId + ".py";
            Path testFilePath = Paths.get("src/scripts/python_tasks/" + testFileName);

            if (!Files.exists(testFilePath)) {
                result.setSuccess(false);
                result.setErrorMessage("Nie znaleziono zadania o podanym ID.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            // Skopiuj plik testowy do katalogu roboczego
            Path copiedTestFilePath = workingDir.resolve(testFileName);
            Files.copy(testFilePath, copiedTestFilePath, StandardCopyOption.REPLACE_EXISTING);

            // Skopiuj plik taskexecutor.py do katalogu roboczego
            Path taskExecutorPath = Paths.get("src/scripts/python_tasks/TaskExecutor.py");
            Path copiedTaskExecutorPath = workingDir.resolve("TaskExecutor.py");
            Files.copy(taskExecutorPath, copiedTaskExecutorPath, StandardCopyOption.REPLACE_EXISTING);

            // Dodaj import do kodu użytkownika w pliku testowym
            String testFileContent = Files.readString(copiedTestFilePath);
            String updatedTestFileContent = "import user_code\n" + testFileContent;
            Files.writeString(copiedTestFilePath, updatedTestFileContent);

            // Uruchomienie zadania w kontenerze Docker
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

            // Pobierz wynik przechwyconego wyjścia użytkownika
            Path userOutputPath = workingDir.resolve("user_output.txt");
            if (Files.exists(userOutputPath)) {
                String userOutput = Files.readString(userOutputPath);
                result.setUserOutput(userOutput);
            } else {
                result.setUserOutput("");
            }

            result.setSuccess(true);

            // Usuń katalog roboczy
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
            Path mainFilePath = Paths.get("src/scripts/java_tasks/" + mainFileName);

            if (!Files.exists(mainFilePath)) {
                result.setSuccess(false);
                result.setErrorMessage("Nie znaleziono zadania o podanym ID.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            // Skopiuj plik TaskExecutor.java do katalogu roboczego
            Path executorFilePath = Paths.get("src/scripts/java_tasks/TaskExecutor.java");
            if (Files.exists(executorFilePath)) {
                Path combinedExecutorFilePath = workingDir.resolve("TaskExecutor.java");
                Files.copy(executorFilePath, combinedExecutorFilePath, StandardCopyOption.REPLACE_EXISTING);
            } else {
                result.setSuccess(false);
                result.setErrorMessage("Nie znaleziono klasy TaskExecutor.");
                deleteDirectory(workingDir.toFile());
                return result;
            }

            Path combinedMainFilePath = workingDir.resolve(mainFileName);
            Files.copy(mainFilePath, combinedMainFilePath, StandardCopyOption.REPLACE_EXISTING);

            String compileCommand = "javac Result.java TaskExecutor.java " + mainFileName;
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

    public Assignment createAssignment(String title, String description, Long lessonId, Title titleLvl) {
        Optional<Lesson> lessonOpt = lessonRepository.findById(lessonId);
        if (lessonOpt.isEmpty()){
            throw new RuntimeException("Lesson not found");
        }
        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setLesson(lessonOpt.get());
        assignment.setTitleLvl(titleLvl);
        assignment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        assignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
    public Optional<AssignmentResponse> getAssignmentById(Long id) {
        // Pobierz obiekt Authentication z SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        // Pobierz nazwe użytkownika
        String username = authentication.getName();

        // Pobierz role użytkownika
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));


        // Znajdź Assignment na podstawie id
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(id);

        // Jeśli Assignment istnieje, zwróć AssignmentResponse, jeśli nie - zwróć Optional.empty()
        return assignmentOptional.map(assignment -> new AssignmentResponse(assignment, roles));
    }

    public Map<String, Object> getAllSubmissions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        // Znajdź wszystkie kursy, lekcje i zadania, dla których użytkownik wykonał przynajmniej jedno przesłanie
        List<Course> courses = courseRepository.findAll();
        Map<String, Object> response = new HashMap<>();

        for (Course course : courses) {
            List<Lesson> lessons = lessonRepository.findByCourseCourseId(course.getCourseId());
            List<Map<String, Object>> lessonsData = new ArrayList<>();

            for (Lesson lesson : lessons) {
                List<Assignment> assignments = assignmentRepository.findByLessonLessonId(lesson.getLessonId());
                List<Map<String, Object>> assignmentsData = new ArrayList<>();

                for (Assignment assignment : assignments) {
                    Submission latestSubmission = submissionRepository.findTopByUserUserIdAndAssignmentAssignmentIdOrderBySubmittedAtDesc(
                            user.getUserId(), assignment.getAssignmentId()).orElse(null);

                    if (latestSubmission != null) {
                        Map<String, Object> assignmentData = new HashMap<>();
                        assignmentData.put("assignmentTitle", assignment.getTitle());

                        Map<String, Object> submissionData = new HashMap<>();
                        submissionData.put("grade", latestSubmission.getGrade());
                        submissionData.put("submittedAt", latestSubmission.getSubmittedAt());
                        submissionData.put("content", latestSubmission.getContent());
                        assignmentData.put("submission", submissionData);

                        assignmentsData.add(assignmentData);
                    }
                }

                if (!assignmentsData.isEmpty()) {
                    Map<String, Object> lessonData = new HashMap<>();
                    lessonData.put("lessonTitle", lesson.getTitle());
                    lessonData.put("assignments", assignmentsData);
                    lessonsData.add(lessonData);
                }
            }

            if (!lessonsData.isEmpty()) {
                Map<String, Object> courseData = new HashMap<>();
                courseData.put("courseTitle", course.getTitle());
                courseData.put("lessons", lessonsData);
                response.put(course.getTitle(), courseData);
            }
        }

        return response;
    }
    public List<AssignmentDTO> getAllAssignmentsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("User is not authenticated");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        Title userTitle = user.getTitle();
        int userLessonLevel = user.getLevel();
        List<Assignment> allAssignments = assignmentRepository.findAll();
        return allAssignments.stream()
                .map(assignment -> {
                    boolean isTitleLevelMatch = userTitle.ordinal() >= assignment.getTitleLvl().ordinal();
                    boolean isLessonLevelMatch = userLessonLevel >= assignment.getLesson().getRequiredLevel();
                    boolean available = isTitleLevelMatch && isLessonLevelMatch;
                    return new AssignmentDTO(assignment, available);
                })
                .collect(Collectors.toList());
    }

    public Optional<Assignment> updateAssignment(Long id, Assignment updatedAssignment) {
        return assignmentRepository.findById(id).map(existingAssignment -> {
            existingAssignment.setTitle(updatedAssignment.getTitle());
            existingAssignment.setDescription(updatedAssignment.getDescription());
            existingAssignment.setLesson(updatedAssignment.getLesson());
            existingAssignment.setTitleLvl(updatedAssignment.getTitleLvl());
            existingAssignment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            existingAssignment.setCreatedAt(existingAssignment.getCreatedAt());
            return assignmentRepository.save(existingAssignment);
        });
    }

}