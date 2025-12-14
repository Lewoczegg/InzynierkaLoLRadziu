package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.adapter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper.UserMapperMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.repository.UserRepositoryMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("mysql")
@Component
public class UserRepositoryAdapterMySQL implements UserRepositoryAdapter {

    private final UserRepositoryMySQL userRepository;
    private final UserMapperMySQL userMapper;

    public UserRepositoryAdapterMySQL(UserRepositoryMySQL userRepository, UserMapperMySQL userMapper) {
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
