package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Lesson;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Progress;

import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUserUserIdAndLessonLessonId(Long userId, Long lessonId);
    Long countByUserUserId(Long user_Id);
}

