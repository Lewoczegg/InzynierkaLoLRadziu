package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document.UserDocument;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.RoleDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;

import java.sql.Timestamp;

@Component
public class UserMapperMongoDB {

    public UserDomain toDomain(UserDocument document) {
        if (document == null) {
            return null;
        }

        RoleDomain role = null;
        if (document.getRole() != null) {
            role = new RoleDomain();
            String roleStr = document.getRole();
            role.setRoleId(("ADMIN".equals(roleStr) || "ROLE_ADMIN".equals(roleStr)) ? 1L : 2L);
            String roleName = roleStr;
            if (!roleName.startsWith("ROLE_")) {
                roleName = "ROLE_" + roleName;
            }
            role.setRoleName(roleName);
        }

        UserDomain domain = UserDomain.builder()
                .userId(document.getId())
                .username(document.getUsername())
                .email(document.getEmail())
                .password(document.getPassword())
                .firstName(document.getFirstName())
                .surname(document.getSurname())
                .age(document.getAge())
                .phoneNumber(document.getPhoneNumber())
                .level(document.getLevel())
                .title(document.getTitle())
                .role(role)
                .build();
        
        if (document.getCreatedAt() != null) {
            domain.setCreatedAt(Timestamp.from(document.getCreatedAt()));
        }
        if (document.getUpdatedAt() != null) {
            domain.setUpdatedAt(Timestamp.from(document.getUpdatedAt()));
        }
        
        return domain;
    }

    public UserDocument toDocument(UserDomain domain) {
        if (domain == null) {
            return null;
        }

        UserDocument document = new UserDocument();
        document.setId(domain.getUserId());
        document.setUsername(domain.getUsername());
        document.setEmail(domain.getEmail());
        document.setPassword(domain.getPassword());
        document.setFirstName(domain.getFirstName());
        document.setSurname(domain.getSurname());
        document.setAge(domain.getAge());
        document.setPhoneNumber(domain.getPhoneNumber());
        document.setLevel(domain.getLevel());
        document.setTitle(domain.getTitle());
        
        if (domain.getRole() != null) {
            String roleName = domain.getRole().getRoleName();
            if (roleName.startsWith("ROLE_")) {
                roleName = roleName.substring(5); 
            }
            document.setRole(roleName);
        }
        
        if (domain.getCreatedAt() != null) {
            document.setCreatedAt(domain.getCreatedAt().toInstant());
        }
        if (domain.getUpdatedAt() != null) {
            document.setUpdatedAt(domain.getUpdatedAt().toInstant());
        }
        
        return document;
    }
}
