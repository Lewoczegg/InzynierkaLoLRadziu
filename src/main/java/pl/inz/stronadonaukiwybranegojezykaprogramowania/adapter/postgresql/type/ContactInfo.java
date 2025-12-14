package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfo implements Serializable {
    private String email;
    private String phoneNumber;
}
