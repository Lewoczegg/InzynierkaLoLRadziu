package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity.RoleEntityPostgres;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.RoleDomain;

@Component
public class RoleMapperPostgres {

    public RoleDomain toDomain(RoleEntityPostgres entity) {
        if (entity == null) {
            return null;
        }
        
        RoleDomain domain = new RoleDomain();
        domain.setRoleId(entity.getRoleId());
        domain.setRoleName(entity.getRoleName());
        return domain;
    }

    public RoleEntityPostgres toEntity(RoleDomain domain) {
        if (domain == null) {
            return null;
        }
        
        RoleEntityPostgres entity = new RoleEntityPostgres();
        entity.setRoleId(domain.getRoleId());
        entity.setRoleName(domain.getRoleName());
        return entity;
    }
}
