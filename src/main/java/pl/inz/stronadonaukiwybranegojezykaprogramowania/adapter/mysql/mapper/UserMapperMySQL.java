package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.UserEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

@Component
public class UserMapperMySQL {

    private final RoleMapperMySQL roleMapper;

    public UserMapperMySQL(RoleMapperMySQL roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserDomain toDomain(UserEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        return UserDomain.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .firstName(entity.getFirstName())
                .surname(entity.getSurname())
                .age(entity.getAge())
                .phoneNumber(entity.getPhoneNumber())
                .role(roleMapper.toDomain(entity.getRole()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .title(entity.getTitle())
                .level(entity.getLevel())
                .build();
    }

    public UserEntityMySQL toEntity(UserDomain domain) {
        if (domain == null) {
            return null;
        }
        
        return UserEntityMySQL.builder()
                .userId(domain.getUserId())
                .username(domain.getUsername())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .firstName(domain.getFirstName())
                .surname(domain.getSurname())
                .age(domain.getAge())
                .phoneNumber(domain.getPhoneNumber())
                .role(roleMapper.toEntity(domain.getRole()))
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .title(domain.getTitle())
                .level(domain.getLevel())
                .build();
    }
}
