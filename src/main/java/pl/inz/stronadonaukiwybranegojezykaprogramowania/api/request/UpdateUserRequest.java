package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private Integer age;

    // Getters and setters
}