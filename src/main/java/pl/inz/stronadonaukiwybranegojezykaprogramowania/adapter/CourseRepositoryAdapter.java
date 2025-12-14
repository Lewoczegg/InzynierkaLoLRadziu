package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.CourseDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CourseRepositoryAdapter {
    List<CourseDomain> findByTitleLvl(Title title);
    List<CourseDomain> findByTitleLvlIn(Collection<Title> titles);
    CourseDomain save(CourseDomain course);
    Optional<CourseDomain> findById(Long id);
    List<CourseDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
