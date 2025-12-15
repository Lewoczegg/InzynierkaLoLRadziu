package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.RoleRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.RoleDomain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * MongoDB doesn't have a separate roles collection - roles are stored as strings in user documents.
 * This adapter provides a simple in-memory implementation for the standard roles.
 */
@Profile("mongodb")
@Component
public class RoleRepositoryAdapterMongoDB implements RoleRepositoryAdapter {

    private static final List<RoleDomain> ROLES = Arrays.asList(
            createRole(1L, "USER"),
            createRole(2L, "ADMIN")
    );

    private static RoleDomain createRole(Long id, String name) {
        RoleDomain role = new RoleDomain();
        role.setRoleId(id);
        role.setRoleName(name);
        return role;
    }

    @Override
    public Optional<RoleDomain> findRoleByroleId(Long id) {
        return ROLES.stream()
                .filter(role -> role.getRoleId().equals(id))
                .findFirst();
    }

    @Override
    public RoleDomain save(RoleDomain role) {
        // In MongoDB, roles are not stored separately, they're part of user documents
        // This is a no-op for MongoDB
        return role;
    }

    @Override
    public Optional<RoleDomain> findById(Long id) {
        return findRoleByroleId(id);
    }

    @Override
    public List<RoleDomain> findAll() {
        return ROLES;
    }

    @Override
    public void deleteById(Long id) {
        // No-op for MongoDB
    }

    @Override
    public boolean existsById(Long id) {
        return ROLES.stream().anyMatch(role -> role.getRoleId().equals(id));
    }

    @Override
    public long count() {
        return ROLES.size();
    }
}
