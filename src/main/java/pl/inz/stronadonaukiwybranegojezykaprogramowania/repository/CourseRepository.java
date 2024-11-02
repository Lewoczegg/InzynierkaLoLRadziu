package pl.inz.stronadonaukiwybranegojezykaprogramowania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.Course;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitleLvl(Title title);
    List<Course> findByTitleLvlIn(Collection<Title> titles);
//    List<Course> findAll();
}
