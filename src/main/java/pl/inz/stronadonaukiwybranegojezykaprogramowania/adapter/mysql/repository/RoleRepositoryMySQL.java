package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.RoleEntityMySQL;

import java.util.Optional;

@Repository
public interface RoleRepositoryMySQL extends JpaRepository<RoleEntityMySQL, Long> {
    Optional<RoleEntityMySQL> findRoleByroleId(Long id);
}
