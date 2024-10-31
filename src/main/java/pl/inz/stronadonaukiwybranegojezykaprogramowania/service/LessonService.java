package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Course;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.CourseRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.LessonRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;

    // Tworzenie nowej lekcji
    public Lesson createLesson(String title, String content, Long courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        Lesson lesson = new Lesson();
        lesson.setTitle(title);
        lesson.setContent(content);
        lesson.setCourse(courseOpt.get());  // Związanie z kursem
        lesson.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        lesson.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return lessonRepository.save(lesson);
    }

    // Pobieranie wszystkich lekcji
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    // Pobieranie lekcji po ID
    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }
}