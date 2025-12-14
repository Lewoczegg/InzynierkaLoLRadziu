package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryAdapter {
    UserDomain findByUsername(String username);
    UserDomain save(UserDomain user);
    Optional<UserDomain> findById(Long id);
    List<UserDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
