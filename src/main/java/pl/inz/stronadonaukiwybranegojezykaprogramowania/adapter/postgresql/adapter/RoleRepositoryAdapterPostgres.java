package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.RoleRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.RoleMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.RoleRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.RoleDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoleRepositoryAdapterPostgres implements RoleRepositoryAdapter {

    private final RoleRepositoryPostgres roleRepository;
    private final RoleMapperPostgres roleMapper;

    public RoleRepositoryAdapterPostgres(RoleRepositoryPostgres roleRepository, RoleMapperPostgres roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Optional<RoleDomain> findRoleByroleId(Long id) {
        return roleRepository.findRoleByroleId(id)
                .map(roleMapper::toDomain);
    }

    @Override
    public RoleDomain save(RoleDomain role) {
        return roleMapper.toDomain(
                roleRepository.save(roleMapper.toEntity(role))
        );
    }

    @Override
    public Optional<RoleDomain> findById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDomain);
    }

    @Override
    public List<RoleDomain> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }
}
