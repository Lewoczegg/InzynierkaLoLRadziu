package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type;

import lombok.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLevel implements Serializable {
    private Integer level;
    private Title title;
}
