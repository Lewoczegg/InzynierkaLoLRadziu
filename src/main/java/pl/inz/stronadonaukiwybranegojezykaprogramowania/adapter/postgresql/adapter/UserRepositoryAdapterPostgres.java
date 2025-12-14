package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper.UserMapperPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.repository.UserRepositoryPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("postgresql")
@Component
public class UserRepositoryAdapterPostgres implements UserRepositoryAdapter {

    private final UserRepositoryPostgres userRepository;
    private final UserMapperPostgres userMapper;

    public UserRepositoryAdapterPostgres(UserRepositoryPostgres userRepository, UserMapperPostgres userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDomain findByUsername(String username) {
        return userMapper.toDomain(userRepository.findByUsername(username));
    }

    @Override
    public UserDomain save(UserDomain user) {
        return userMapper.toDomain(
                userRepository.save(userMapper.toEntity(user))
        );
    }

    @Override
    public Optional<UserDomain> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public List<UserDomain> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
