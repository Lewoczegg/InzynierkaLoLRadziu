package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullName implements Serializable {
    private String firstName;
    private String surname;
}
