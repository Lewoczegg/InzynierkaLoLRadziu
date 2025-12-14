package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.CourseEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseRepositoryMySQL extends JpaRepository<CourseEntityMySQL, Long> {
    List<CourseEntityMySQL> findByTitleLvl(Title title);
    List<CourseEntityMySQL> findByTitleLvlIn(Collection<Title> titles);
}
