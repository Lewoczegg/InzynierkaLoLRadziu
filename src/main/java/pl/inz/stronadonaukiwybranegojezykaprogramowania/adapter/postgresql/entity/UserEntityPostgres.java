package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class UserEntityPostgres {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Type(FullNameType.class)
    @Column(name = "name", columnDefinition = "full_name")
    private FullName name;

    @Type(ContactInfoType.class)
    @Column(name = "contacts", columnDefinition = "contact_info")
    private ContactInfo contacts;

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntityPostgres role;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Type(UserLevelType.class)
    @Column(name = "level_info", columnDefinition = "user_level")
    private UserLevel levelInfo;
}
