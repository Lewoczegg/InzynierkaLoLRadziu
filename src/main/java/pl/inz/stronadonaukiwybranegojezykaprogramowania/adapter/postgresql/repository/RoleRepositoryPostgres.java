package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.RoleEntityPostgres;

import java.util.Optional;

@Repository
public interface RoleRepositoryPostgres extends JpaRepository<RoleEntityPostgres, Long> {
    Optional<RoleEntityPostgres> findRoleByroleId(Long id);
}
