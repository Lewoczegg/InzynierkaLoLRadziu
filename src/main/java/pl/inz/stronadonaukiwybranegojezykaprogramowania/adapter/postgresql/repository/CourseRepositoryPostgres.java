package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.CourseEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseRepositoryPostgres extends JpaRepository<CourseEntityPostgres, Long> {
    List<CourseEntityPostgres> findByTitleLvl(Title title);
    List<CourseEntityPostgres> findByTitleLvlIn(Collection<Title> titles);
}
