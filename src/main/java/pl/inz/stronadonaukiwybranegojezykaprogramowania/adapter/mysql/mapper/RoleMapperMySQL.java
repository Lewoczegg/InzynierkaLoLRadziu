package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.mapper;

import org.springframework.stereotype.Component;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mysql.entity.RoleEntityMySQL;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.RoleDomain;

@Component
public class RoleMapperMySQL {

    public RoleDomain toDomain(RoleEntityMySQL entity) {
        if (entity == null) {
            return null;
        }
        
        RoleDomain domain = new RoleDomain();
        domain.setRoleId(entity.getRoleId());
        domain.setRoleName(entity.getRoleName());
        return domain;
    }

    public RoleEntityMySQL toEntity(RoleDomain domain) {
        if (domain == null) {
            return null;
        }
        
        RoleEntityMySQL entity = new RoleEntityMySQL();
        entity.setRoleId(domain.getRoleId());
        entity.setRoleName(domain.getRoleName());
        return entity;
    }
}
