package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
public class UserDocument {

    @Id
    private Long id; // Use Long ID directly (matches SQL)

    @Field("username")
    private String username;

    @Field("email")
    private String email;

    @Field("password")
    private String password;

    @Field("firstName")
    private String firstName;

    @Field("surname")
    private String surname;

    @Field("age")
    private Integer age;

    @Field("phoneNumber")
    private String phoneNumber;

    @Field("level")
    private Integer level;

    @Field("role")
    private String role;

    @Field("title")
    private Title title;

    @Field("createdAt")
    private Instant createdAt;

    @Field("updatedAt")
    private Instant updatedAt;

    @Field("progress")
    private List<ProgressEmbedded> progress;

    @Field("quizResults")
    private List<QuizResultEmbedded> quizResults;

    @Field("dailyTasks")
    private List<DailyTaskAssignmentEmbedded> dailyTasks;
}
