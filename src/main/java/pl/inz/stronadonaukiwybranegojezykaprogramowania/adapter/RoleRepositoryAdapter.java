package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.RoleDomain;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryAdapter {
    Optional<RoleDomain> findRoleByroleId(Long id);
    RoleDomain save(RoleDomain role);
    Optional<RoleDomain> findById(Long id);
    List<RoleDomain> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
