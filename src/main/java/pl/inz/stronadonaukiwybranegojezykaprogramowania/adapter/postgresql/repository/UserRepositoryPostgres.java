package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.UserEntityPostgres;

@Repository
public interface UserRepositoryPostgres extends JpaRepository<UserEntityPostgres, Long> {
    UserEntityPostgres findByUsername(String username);
}
