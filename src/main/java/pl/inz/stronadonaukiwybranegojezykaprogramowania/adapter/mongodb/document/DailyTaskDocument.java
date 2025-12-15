package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "daily_tasks")
public class DailyTaskDocument {

    @Id
    private Long id; // Use Long ID directly (matches SQL)

    @Field("title")
    private String title;

    @Field("description")
    private String description;
}
