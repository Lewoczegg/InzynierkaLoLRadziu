package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CompositeType;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.ContactInfo;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.FullName;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type.UserLevel;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntityPostgres {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @AttributeOverrides({
        @AttributeOverride(name = "firstName", column = @Column(name = "name.first_name")),
        @AttributeOverride(name = "surname", column = @Column(name = "name.surname"))
    })
    @Embedded
    private FullName name;

    @AttributeOverrides({
        @AttributeOverride(name = "email", column = @Column(name = "contacts.email")),
        @AttributeOverride(name = "phoneNumber", column = @Column(name = "contacts.phone_number"))
    })
    @Embedded
    private ContactInfo contacts;

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntityPostgres role;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @AttributeOverrides({
        @AttributeOverride(name = "level", column = @Column(name = "level_info.level")),
        @AttributeOverride(name = "title", column = @Column(name = "level_info.title"))
    })
    @Embedded
    private UserLevel levelInfo;
}
