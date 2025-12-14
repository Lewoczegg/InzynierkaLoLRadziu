package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.RoleRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.RoleMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.RoleRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.RoleDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mysql")
@Component
public class RoleRepositoryAdapterMySQL implements RoleRepositoryAdapter {

    private final RoleRepositoryMySQL roleRepository;
    private final RoleMapperMySQL roleMapper;

    public RoleRepositoryAdapterMySQL(RoleRepositoryMySQL roleRepository, RoleMapperMySQL roleMapper) {
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
