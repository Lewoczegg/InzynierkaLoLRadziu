package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.UserEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.ContactInfo;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.FullName;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.UserLevel;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

@Component
public class UserMapperPostgres {

    private final RoleMapperPostgres roleMapper;

    public UserMapperPostgres(RoleMapperPostgres roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserDomain toDomain(UserEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        return UserDomain.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .email(entity.getContacts() != null ? entity.getContacts().getEmail() : null)
                .password(entity.getPassword())
                .firstName(entity.getName() != null ? entity.getName().getFirstName() : null)
                .surname(entity.getName() != null ? entity.getName().getSurname() : null)
                .age(entity.getAge())
                .phoneNumber(entity.getContacts() != null ? entity.getContacts().getPhoneNumber() : null)
                .role(roleMapper.toDomain(entity.getRole()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .title(entity.getLevelInfo() != null ? entity.getLevelInfo().getTitle() : null)
                .level(entity.getLevelInfo() != null ? entity.getLevelInfo().getLevel() : null)
                .build();
    }

    public UserEntityPostgres toEntity(UserDomain domain) {
        if (domain == null) {
            return null;
        }
        
        FullName fullName = FullName.builder()
                .firstName(domain.getFirstName())
                .surname(domain.getSurname())
                .build();
        
        ContactInfo contactInfo = ContactInfo.builder()
                .email(domain.getEmail())
                .phoneNumber(domain.getPhoneNumber())
                .build();
        
        UserLevel userLevel = UserLevel.builder()
                .level(domain.getLevel())
                .title(domain.getTitle())
                .build();
        
        return UserEntityPostgres.builder()
                .userId(domain.getUserId())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .name(fullName)
                .contacts(contactInfo)
                .age(domain.getAge())
                .role(roleMapper.toEntity(domain.getRole()))
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .levelInfo(userLevel)
                .build();
    }
}
