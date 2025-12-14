package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.UserEntityMySQL;

@Repository
public interface UserRepositoryMySQL extends JpaRepository<UserEntityMySQL, Long> {
    UserEntityMySQL findByUsername(String username);
}
