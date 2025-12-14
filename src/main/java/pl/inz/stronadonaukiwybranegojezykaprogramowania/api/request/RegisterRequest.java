package pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private Integer age;
}
