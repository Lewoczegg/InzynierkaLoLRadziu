package pl.inz.stronadonaukiwybranegojezykaprogramowania.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDomain implements UserDetails {
    
    private Long userId;

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String surname;

    private Integer age;

    private String phoneNumber;

    private RoleDomain role;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Title title;

    private Integer level;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
